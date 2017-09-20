package com.ujujzk.vrgweather.data.storage;

import com.orhanobut.logger.Logger;
import com.ujujzk.vrgweather.model.app.AppCity;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Single;

/**
 * Created by ujujzk on 20.09.2017
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

public class RealmWeatherStorage implements WeatherStorage {

    private static volatile RealmWeatherStorage instance;

    private RealmWeatherStorage() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new AssertionError("Use getInst() method to get the single instance of this class.");
        }
    }

    public static RealmWeatherStorage getInst() {
        if (instance == null) {
            synchronized (RealmWeatherStorage.class) {
                if (instance == null) {
                    instance = new RealmWeatherStorage();
                }
            }
        }
        return instance;
    }

    @Override
    public Single<AppCity> getWeather(String cityName) {
        return Single.create(singleSubscriber -> {
            try {
                Realm realm = Realm.getDefaultInstance();

                AppCity city =
                        realm.where(AppCity.class)
                                .equalTo("cityName", cityName, Case.INSENSITIVE)
                                .findFirst();

                singleSubscriber.onSuccess(city);

            } catch (Exception ex) {
                Logger.e(ex, "Realm threw exception");
                singleSubscriber.onError(ex);
            }
        });
    }

    @Override
    public Observable<AppCity> getCities() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<AppCity> cities =
                realm.where(AppCity.class)
                        .findAll();



        return Observable.from(cities);
    }

    @Override
    public void saveCity(AppCity city) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(bgRealm ->
            bgRealm.copyToRealmOrUpdate(city));
    }
}

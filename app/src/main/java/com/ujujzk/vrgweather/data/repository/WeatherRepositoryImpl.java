package com.ujujzk.vrgweather.data.repository;

import com.orhanobut.logger.Logger;
import com.ujujzk.vrgweather.App;
import com.ujujzk.vrgweather.data.api.OpenWeatherManager;
import com.ujujzk.vrgweather.data.api.WeatherManager;
import com.ujujzk.vrgweather.data.storage.RealmWeatherStorage;
import com.ujujzk.vrgweather.data.storage.WeatherStorage;
import com.ujujzk.vrgweather.model.OpenWeatherMapper;
import com.ujujzk.vrgweather.model.app.AppCity;
import com.ujujzk.vrgweather.model.openweather.Weather;

import java.util.Calendar;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;


public class WeatherRepositoryImpl implements WeatherRepository {

    private static final long MAX_TIME_DIFF_HOURS = 3L;

    WeatherManager weatherManager;
    WeatherStorage weatherStorage;


    private static volatile WeatherRepositoryImpl instance;

    private WeatherRepositoryImpl() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new AssertionError("Use getInstance() method to get the single instance of this class.");
        }
        weatherManager = OpenWeatherManager.getInst();
        weatherStorage = RealmWeatherStorage.getInst();
    }

    public static WeatherRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (OpenWeatherManager.class) {
                if (instance == null) {
                    instance = new WeatherRepositoryImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public Single<AppCity> getWeather(String cityName) {

        return Single.create(singleSubscriber -> {

            getWeatherLocal(cityName)
                    .subscribe(localCity -> {
                        if (localCity == null || getTimeDiffHours(localCity.getDate()) > MAX_TIME_DIFF_HOURS){
                            //no city in local or city in local is not up-to-date
                            getWeatherRemote(cityName).subscribe(singleSubscriber::onSuccess);
                        } else {
                            //city in local is up-to-date
                            singleSubscriber.onSuccess(localCity);
                        }
                    },throwable ->
                        getWeatherRemote(cityName).subscribe(singleSubscriber::onSuccess)
                    );
        });

    }

    @Override
    public Single<AppCity> getWeatherRemote(String cityName) {
        return weatherManager.getWeather(cityName)
                .map(openWeather -> {
                    AppCity city = OpenWeatherMapper.makeCity(openWeather);
                    weatherStorage.saveCity(city);
                    return city;
                });
    }

    @Override
    public Single<AppCity> getWeatherLocal(String cityName) {
        return weatherStorage.getWeather(cityName);
    }

    @Override
    public Observable<AppCity> getCitiesLocal() {
        return weatherStorage.getCities();
    }


    private long getTimeDiffHours (long timeMillis) {
        long diff = Calendar.getInstance().getTimeInMillis() - timeMillis;
        long diffHours = diff / (60 * 60 * 1000) % 60;
        return diffHours;
    }
}

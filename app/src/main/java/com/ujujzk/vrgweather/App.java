package com.ujujzk.vrgweather;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.singhajit.sherlock.core.Sherlock;
import com.ujujzk.vrgweather.data.api.OpenWeatherManager;
import com.ujujzk.vrgweather.model.OpenWeatherMapper;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);

        if (!BuildConfig.DEBUG) {
            Logger.init().logLevel(LogLevel.NONE);
        } else {
            Sherlock.init(this);
            Sherlock.getInstance().getAllCrashes();
        }
    }
}

package com.ujujzk.vrgweather;

import android.app.Application;

import com.ujujzk.vrgweather.di.DaggerDataComponent;
import com.ujujzk.vrgweather.di.DataComponent;
import com.ujujzk.vrgweather.di.DataModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {

    private static DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);


        dataComponent = DaggerDataComponent
                .builder()
                .dataModule(new DataModule(this))
                .build();


    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }
}

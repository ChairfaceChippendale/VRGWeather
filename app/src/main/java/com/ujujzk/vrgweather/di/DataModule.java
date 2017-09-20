package com.ujujzk.vrgweather.di;

import android.app.Application;

import com.ujujzk.vrgweather.data.api.OpenWeatherManager;
import com.ujujzk.vrgweather.data.api.WeatherManager;
import com.ujujzk.vrgweather.data.repository.WeatherRepository;
import com.ujujzk.vrgweather.data.repository.WeatherRepositoryImpl;
import com.ujujzk.vrgweather.data.storage.RealmWeatherStorage;
import com.ujujzk.vrgweather.data.storage.WeatherStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ujujzk on 05.07.2017
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Module
public class DataModule {

    private final Application app;

    public DataModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    WeatherRepository provideDataProvider() {
        return WeatherRepositoryImpl.getInst();
    }

    @Provides
    @Singleton
    WeatherStorage provideUserManager() {
        return RealmWeatherStorage.getInst();
    }


    @Provides
    @Singleton
    WeatherManager providePaymentManager() {
        return OpenWeatherManager.getInst(app);
    }

}

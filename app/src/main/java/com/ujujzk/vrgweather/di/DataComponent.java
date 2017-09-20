package com.ujujzk.vrgweather.di;


import com.ujujzk.vrgweather.data.repository.WeatherRepositoryImpl;
import com.ujujzk.vrgweather.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {DataModule.class})
public interface DataComponent {

    void inject(WeatherRepositoryImpl weatherRepository);

    void inject(MainPresenter mainPresenter);


}

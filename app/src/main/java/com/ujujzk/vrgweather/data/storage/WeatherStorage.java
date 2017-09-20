package com.ujujzk.vrgweather.data.storage;


import com.ujujzk.vrgweather.model.app.AppCity;

import rx.Observable;
import rx.Single;

public interface WeatherStorage {

    Single<AppCity> getWeather(String cityName);

    Observable<AppCity> getCities ();

    void saveCity(AppCity city);
}

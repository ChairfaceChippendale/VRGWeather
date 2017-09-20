package com.ujujzk.vrgweather.data.repository;


import com.ujujzk.vrgweather.model.app.AppCity;

import rx.Observable;
import rx.Single;



public interface WeatherRepository {

    Single<AppCity> getWeather(String cityName);

    Single<AppCity> getWeatherRemote(String cityName);
    
    Single<AppCity> getWeatherLocal(String cityName);

    Observable<AppCity> getCitiesLocal ();
}

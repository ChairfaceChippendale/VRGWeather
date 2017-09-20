package com.ujujzk.vrgweather.data.api;


import com.ujujzk.vrgweather.model.openweather.Example;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;


public interface OpenWeatherApi {

    @GET("/data/2.5/forecast")
    Single<Example> getWeather(
            @Query("q") String cityName,
            @Query("appid") String appId,
            @Query("units") String units,
            @Query("lang") String lang
    );
}

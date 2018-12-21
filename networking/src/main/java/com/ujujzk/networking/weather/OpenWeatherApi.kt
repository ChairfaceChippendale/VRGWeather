package com.ujujzk.networking.weather


import com.ujujzk.networking.weather.model.Example
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {

    @GET("/data/2.5/forecast")
    fun getWeather(
            @Query("q") cityName: String,
            @Query("appid") appId: String,
            @Query("units") units: String,
            @Query("lang") lang: String
    ): Single<Example>
}

package com.ujujzk.domain.gateway

import com.ujujzk.domain.model.City
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherGateway {

    fun getWeather(city: String): Single<City>

    fun getWeatherRemote(cityName: String): Single<City>

    fun getWeatherLocal(cityName: String): Single<City>

    fun getCitiesLocal(): Observable<City>
}
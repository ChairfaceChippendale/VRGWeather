package com.ujujzk.domain.gateway

import com.ujujzk.domain.model.CityWeather
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherGateway {

    fun findWeather(cityName: String): Single<CityWeather>

    fun getWeather(cityId: Long): Single<CityWeather>

    fun getCitiesLocal(): Single<List<CityWeather>>
}
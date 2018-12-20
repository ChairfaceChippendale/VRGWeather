package com.ujujzk.gateway.weather.sources

import com.ujujzk.domain.model.CityWeather
import io.reactivex.Single

interface WeatherStorage {

    fun putCityWeather(cityWeather: CityWeather) : Single<String>

    fun getCityWeather(cityId: Long) : Single<CityWeather>

    fun findCityWeather(cityName: String) : Single<CityWeather>

    fun getAllCityWeather() : Single<List<CityWeather>>

    fun deleteCityWeather(cityId: Long)

    fun cleanUpCityWeather()
}
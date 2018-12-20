package com.ujujzk.gateway.weather.sources

import com.ujujzk.domain.model.CityWeather
import io.reactivex.Single

interface WeatherService {

    fun findCityWeather(cityName: String) : Single<CityWeather>

}
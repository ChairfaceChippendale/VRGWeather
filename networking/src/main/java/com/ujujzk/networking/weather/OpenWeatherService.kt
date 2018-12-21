package com.ujujzk.networking.weather

import com.ujujzk.domain.model.CityWeather
import com.ujujzk.gateway.weather.sources.WeatherService
import com.ujujzk.networking.OPEN_WEATHER_APP_ID
import com.ujujzk.networking.OPEN_WEATHER_UNITS
import com.ujujzk.networking.weather.model.WeatherFromServiceToDomainMapper
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class OpenWeatherService
    @Inject constructor(val weatherApi: OpenWeatherApi)
    : WeatherService {

    override fun findCityWeather(cityName: String): Single<CityWeather> {
        return weatherApi.getWeather(
                cityName,
                OPEN_WEATHER_APP_ID,
                OPEN_WEATHER_UNITS,
                Locale.getDefault().getLanguage()).map (WeatherFromServiceToDomainMapper())
    }
}
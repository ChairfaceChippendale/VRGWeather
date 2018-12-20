package com.ujujzk.gateway.weather

import com.ujujzk.domain.gateway.WeatherGateway
import com.ujujzk.domain.model.CityWeather
import com.ujujzk.gateway.weather.sources.WeatherService
import com.ujujzk.gateway.weather.sources.WeatherStorage
import io.reactivex.Single
import javax.inject.Inject

class WeatherGatewayImpl
    @Inject constructor(
            val weatherService: WeatherService,
            val weatherStorage: WeatherStorage
    ): WeatherGateway {

    override fun findWeather(cityName: String): Single<CityWeather> {
        return weatherStorage.findCityWeather(cityName)
                .onErrorResumeNext { weatherService.findCityWeather(cityName) }
    }

    override fun getWeather(cityId: Long): Single<CityWeather> = weatherStorage.getCityWeather(cityId)

    override fun getCitiesLocal(): Single<List<CityWeather>> = weatherStorage.getAllCityWeather()

}
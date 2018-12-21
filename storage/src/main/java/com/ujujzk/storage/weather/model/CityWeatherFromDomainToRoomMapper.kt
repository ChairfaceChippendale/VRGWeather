package com.ujujzk.storage.weather.model

import com.ujujzk.domain.model.CityWeather
import io.reactivex.functions.Function

class CityWeatherFromDomainToRoomMapper : Function<CityWeather, CityAndWeatherRoom> {

    override fun apply(cityWeather: CityWeather): CityAndWeatherRoom {
        return CityAndWeatherRoom(
                CityRoom(cityWeather.id, cityWeather.cityName, cityWeather.countryCode, cityWeather.date),
                cityWeather.weatherList.map { WeatherRoom(
                        it.id, it.dateStr, it.date, it.description,
                        it.iconLink, it.maxTemperature, it.minTemperature, cityWeather.id) }
        )
    }
}
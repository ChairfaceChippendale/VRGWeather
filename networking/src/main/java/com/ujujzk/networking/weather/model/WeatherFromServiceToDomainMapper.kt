package com.ujujzk.networking.weather.model

import com.ujujzk.domain.model.CityWeather
import io.reactivex.functions.Function
import java.util.*

class WeatherFromServiceToDomainMapper : Function<Example, CityWeather>  {

    val ICON_BASE_LINK = "http://openweathermap.org/img/w/";
    val ICON_BASE_LINK_END = ".png";

    override fun apply(weatherResponse: Example): CityWeather {
        return CityWeather(
                weatherResponse.city.id,
                weatherResponse.city.name,
                weatherResponse.city.country,
                GregorianCalendar().timeInMillis,
                emptyList()
        )
    }
}
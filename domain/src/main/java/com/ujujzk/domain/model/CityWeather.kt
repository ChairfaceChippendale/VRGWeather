package com.ujujzk.domain.model

data class CityWeather(
        val id: Long,
        val cityName: String,
        val countryCode: String,
        val date: Long,
        val weatherList: List<Weather>
)
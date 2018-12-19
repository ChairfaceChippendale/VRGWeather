package com.ujujzk.domain.model

data class City(
        val cityName: String,
        val countryCode: String,
        val date: Long,
        val weatherList: List<Weather>
)
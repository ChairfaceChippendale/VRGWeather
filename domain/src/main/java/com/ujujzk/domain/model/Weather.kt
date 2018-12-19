package com.ujujzk.domain.model

data class Weather(
        val dateStr: String,
        val date: Long,
        val description: String,
        val iconLink: String,

        val maxTemperature: Double,
        val minTemperature: Double
)
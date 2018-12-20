package com.ujujzk.storage.weather.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class WeatherRoom (
        @PrimaryKey val id: Long,
        val dateStr: String,
        val date: Long,
        val description: String,
        val iconLink: String,

        val maxTemperature: Double,
        val minTemperature: Double,

        val cityId: Long
)
package com.ujujzk.storage.weather.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CityRoom (
        @PrimaryKey val id: Long,
        val cityName: String,
        val countryCode: String,
        val date: Long
)
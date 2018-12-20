package com.ujujzk.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ujujzk.storage.weather.WeatherDao
import com.ujujzk.storage.weather.model.CityRoom
import com.ujujzk.storage.weather.model.WeatherRoom

@Database(entities = [CityRoom::class, WeatherRoom::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}
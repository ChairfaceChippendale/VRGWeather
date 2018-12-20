package com.ujujzk.storage.weather.model

data class CityAndWeatherRoom(
        val city: CityRoom,
        val weather: List<WeatherRoom>
)
package com.ujujzk.networking.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WeatherList(
        @SerializedName("dt")
        @Expose
        val dt: Long, //time ms
        @SerializedName("main")
        @Expose
        val main: Main,
        @SerializedName("weather")
        @Expose
        val weather: List<Weather>,
        @SerializedName("wind")
        @Expose
        val wind: Wind,
        @SerializedName("dt_txt")
        @Expose
        val dtTxt: String //date and time as a string
)

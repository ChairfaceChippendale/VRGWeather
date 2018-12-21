package com.ujujzk.networking.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Example (
    @SerializedName("cod")
    @Expose
    val cod: String,
    @SerializedName("message")
    @Expose
    val message: Double,
    @SerializedName("cnt")
    @Expose
    val cnt: Long,
    @SerializedName("list")
    @Expose
    val list: List<WeatherList>,
    @SerializedName("city")
    @Expose
    val city: City
)

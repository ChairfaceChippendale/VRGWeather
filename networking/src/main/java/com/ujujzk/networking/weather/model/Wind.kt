package com.ujujzk.networking.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Wind(
        @SerializedName("speed")
        @Expose
        val speed: Double,
        @SerializedName("deg")
        @Expose
        val deg: Double
)

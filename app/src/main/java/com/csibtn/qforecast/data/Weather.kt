package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "main") val temperature: Temperature,
    @Json(name = "weather") val weatherDescription: WeatherDescription
)

@JsonClass(generateAdapter = true)
data class Temperature(
    val temp: Double,
)

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    val description: String,
)
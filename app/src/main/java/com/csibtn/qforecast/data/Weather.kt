package com.csibtn.qforecast.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "main") val temperature: @RawValue Temperature,
    @Json(name = "weather") val weatherDescription: @RawValue List<WeatherDescription>,
    @Json(name = "dt_txt") val date: String
) : Parcelable

@JsonClass(generateAdapter = true)
data class Temperature(
    val temp: Double,
)

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    val description: String,
    val icon: String
)
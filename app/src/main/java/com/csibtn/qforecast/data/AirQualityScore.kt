package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQualityScore(
    @Json(name = "aqi") val score: Int
)



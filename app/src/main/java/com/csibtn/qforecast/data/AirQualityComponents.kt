package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQualityComponents(
    @Json(name = "main") val quality : AirQualityScore,
    val components : AirComponents
)

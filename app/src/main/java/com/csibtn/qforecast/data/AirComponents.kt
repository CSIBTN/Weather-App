package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirComponents(
    @Json(name = "co") val carbonConcentration: Double,
    @Json(name = "no") val nitrogenConcentration: Double,
    @Json(name = "nh3") val ammoniaConcentration: Double,
)


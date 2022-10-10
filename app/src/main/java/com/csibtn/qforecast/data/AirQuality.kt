package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQuality(
    @Json(name = "list") val airQualityComponents : List<AirQualityComponents>
)



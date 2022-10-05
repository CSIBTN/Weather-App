package com.csibtn.qforecast.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Place(
    val name: String,
    val country: String,
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double,
)



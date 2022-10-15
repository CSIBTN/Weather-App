package com.csibtn.qforecast.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class AirQualityComponents(
    @Json(name = "main") val quality : @RawValue AirQualityScore,
    val components : @RawValue AirComponents
) : Parcelable

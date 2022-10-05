package com.csibtn.qforecast.data

import com.csibtn.qforecast.BuildConfig

const val api_key = BuildConfig.API_KEY

interface WeatherApi {

    suspend fun fetchPlaceLocation ()
}
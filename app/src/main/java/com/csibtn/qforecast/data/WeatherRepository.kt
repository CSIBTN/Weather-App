package com.csibtn.qforecast.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class WeatherRepository {
    private val weatherApi: WeatherApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        weatherApi = retrofit.create()
    }

    suspend fun fetchPlaceLocation(placeName: String) = weatherApi.fetchPlaceLocation(placeName)

    suspend fun fetch5DayForecast(latitude: Double, longitude: Double, units: String = "metric") =
        weatherApi.fetch5dayForecast(latitude, longitude, units)

    suspend fun getAirQualityData(
        latitude: Double,
        longitude: Double,
    ): AirQuality = weatherApi.getAirQualityData(latitude, longitude)
}
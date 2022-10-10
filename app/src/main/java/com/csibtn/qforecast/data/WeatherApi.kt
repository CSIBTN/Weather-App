package com.csibtn.qforecast.data

import com.csibtn.qforecast.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = BuildConfig.API_KEY

interface WeatherApi {

    @GET("geo/1.0/direct?appid=$api_key")
    suspend fun fetchPlaceLocation(@Query("q") placeName: String): List<Place>

    @GET("data/2.5/forecast?&appid=$api_key")
    suspend fun fetch5dayForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): Forecast

    @GET("data/2.5/air_pollution?appid=$api_key")
    suspend fun getAirQualityData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): AirQuality
}
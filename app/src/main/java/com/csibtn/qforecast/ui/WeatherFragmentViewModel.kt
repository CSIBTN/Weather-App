package com.csibtn.qforecast.ui

import androidx.lifecycle.ViewModel
import com.csibtn.qforecast.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherFragmentViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {


    suspend fun getPlaceByName(placeName: String) =
        weatherRepository.fetchPlaceLocation(placeName)

    suspend fun getForecast(latitude: Double, longitude: Double, units: String = "metric") =
        weatherRepository.fetch5DayForecast(latitude, longitude, units).forecastList
}
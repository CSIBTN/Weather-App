package com.csibtn.qforecast.ui

import androidx.lifecycle.ViewModel
import com.csibtn.qforecast.data.AirQualityComponents
import com.csibtn.qforecast.data.Place
import com.csibtn.qforecast.data.Weather
import com.csibtn.qforecast.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherFragmentViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    private var place: Place
    val _place
        get() = place
    private var weather: Map<String, List<Weather>>
    val _weather
        get() = weather

    init {
        weather = emptyMap()
        place = Place("", "", 0.5, 0.5)
    }

    suspend fun getPlaceByName(placeName: String): Place {
        place = weatherRepository.fetchPlaceLocation(placeName)[0]
        return _place
    }

    suspend fun getAirQualityData(
        latitude: Double,
        longitude: Double,
    ): AirQualityComponents =
        weatherRepository.getAirQualityData(latitude, longitude).airQualityComponents[0]

    suspend fun getForecast(
        latitude: Double,
        longitude: Double,
        units: String = "metric"
    ): Map<String, List<Weather>> {

        weather = sortByDays(
            weatherRepository.fetch5DayForecast(
                latitude,
                longitude,
                units
            ).forecastList
        )
        return _weather
    }

    private fun sortByDays(dayForecast: List<Weather>): Map<String, List<Weather>> {
        val dayMap = mutableMapOf<String, MutableList<Weather>>()
        for (day in dayForecast) {
            val date = day.date.split(" ")[0]
            val key = dayMap[date]
            if (key != null) {
                key.add(day)
            } else {
                dayMap[date] = mutableListOf(day)
            }
        }
        return dayMap
    }
}
package com.csibtn.qforecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csibtn.qforecast.data.Place
import com.csibtn.qforecast.data.Weather
import com.csibtn.qforecast.databinding.WeatherItemBinding
import kotlin.math.roundToInt

class WeatherForecastAdapter(private val forecastList: List<Weather>, private val place: Place) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherHolder>() {

    inner class WeatherHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.tvDegrees.text = "${weather.temperature.temp.roundToInt()}°c"
            binding.tvCountry.text = place.country
            binding.tvPlace.text = place.name
            binding.tvWeatherDescription.text = weather.weatherDescription[0].description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(layoutInflater, parent, false)
        return WeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size


}
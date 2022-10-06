package com.csibtn.qforecast.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csibtn.qforecast.data.Place
import com.csibtn.qforecast.data.Weather
import com.csibtn.qforecast.databinding.WeatherItemBinding
import kotlin.math.roundToInt

class CurrentDayWeatherAdapter(
    private val weatherList: List<Weather>,
    private val place: Place,
    private val context: Context
) : RecyclerView.Adapter<CurrentDayWeatherAdapter.CurrentDayHolder>() {
    inner class CurrentDayHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.tvDegrees.text = "${weather.temperature.temp.roundToInt()}°c"
            binding.tvCountry.text = place.country
            binding.tvPlace.text = place.name
            binding.tvDate.text = weather.date
            Glide.with(context)
                .load(imageLink(weather.weatherDescription[0].icon))
                .into(binding.ivWeather)
            binding.tvWeatherDescription.text = weather.weatherDescription[0].description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentDayHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(inflater, parent, false)
        return CurrentDayHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentDayHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    private fun imageLink(iconId: String): String =
        "http://openweathermap.org/img/wn/$iconId@2x.png"
}
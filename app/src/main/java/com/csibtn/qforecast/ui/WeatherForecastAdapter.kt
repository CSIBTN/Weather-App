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

class WeatherForecastAdapter(
    private val airQuality : Int ,
    private val place: Place = Place("", "", 0.5, 0.5),
    private val forecastList: Map<String, List<Weather>>,
    private val context: Context,
    private val onClickCallback: (List<Weather>) -> Unit,
) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherHolder>() {

    inner class WeatherHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherForecastForADay: List<Weather>) {
            when(airQuality){
                1 -> binding.airQualityProgressBar.progress = 100
                2 -> binding.airQualityProgressBar.progress = 80
                3 -> binding.airQualityProgressBar.progress = 50
                4 -> binding.airQualityProgressBar.progress = 20
                else -> binding.airQualityProgressBar.progress = 5
            }
            val weather = weatherForecastForADay[0]
            binding.tvDegrees.text = "${weather.temperature.temp.roundToInt()}°c"
            binding.tvCountry.text = place.country
            binding.tvPlace.text = place.name
            binding.tvDate.text = weather.date
            Glide.with(context)
                .load(imageLink(weather.weatherDescription[0].icon))
                .into(binding.ivWeather)
            binding.tvWeatherDescription.text = weather.weatherDescription[0].description
            binding.root.setOnClickListener {
                onClickCallback(weatherForecastForADay)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(layoutInflater, parent, false)
        return WeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        forecastList[forecastList.keys.toList()[position]]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = forecastList.size

    private fun imageLink(iconId: String): String =
        "http://openweathermap.org/img/wn/$iconId@2x.png"


}
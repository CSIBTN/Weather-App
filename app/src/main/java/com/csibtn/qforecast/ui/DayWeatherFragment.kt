package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.qforecast.databinding.FragmentDayForecastBinding

class DayWeatherFragment : Fragment() {
    private val args: DayWeatherFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDayForecastBinding.inflate(inflater, container, false)
        binding.rfCurrentDayPreview.layoutManager = LinearLayoutManager(context)
        binding.rfCurrentDayPreview.adapter = CurrentDayWeatherAdapter(args.airQuality,args.currentDayWeather.toList(),args.place,requireContext()){
            findNavController().navigate(
                DayWeatherFragmentDirections.showAirQualityChart()
            )
        }
        return binding.root
    }


}
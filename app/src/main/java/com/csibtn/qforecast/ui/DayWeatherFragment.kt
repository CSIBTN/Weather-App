package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.qforecast.databinding.FragmentDayForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DayWeatherFragment : Fragment() {
    private val args: DayWeatherFragmentArgs by navArgs()
    private val viewModel: WeatherFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDayForecastBinding.inflate(inflater, container, false)
        binding.rfCurrentDayPreview.layoutManager = LinearLayoutManager(context)
        binding.rfCurrentDayPreview.adapter = CurrentDayWeatherAdapter(
            args.airQuality,
            args.currentDayWeather.toList(),
            args.place,
            requireContext()
        ) {
            viewLifecycleOwner.lifecycleScope.launch {
                val airQualityData =
                    viewModel.getAirQualityData(args.place.latitude, args.place.longitude)
                findNavController().navigate(
                    DayWeatherFragmentDirections.showAirQualityChart(airQualityData)
                )
            }
        }
        return binding.root
    }


}
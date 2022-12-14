package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.qforecast.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainWeatherFragment() : Fragment() {
    private val viewModel: WeatherFragmentViewModel by viewModels()
    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Check if the view has loaded!"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.rvForecastPreview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val place = viewModel._place
        val weather = viewModel._weather
        binding.rvForecastPreview.adapter =
            WeatherForecastAdapter(
                1,
                forecastList = weather,
                place = place,
                context = requireContext()
            ) { weatherList,_ ->
                findNavController().navigate(
                    MainWeatherFragmentDirections.showFullDayForecast(
                        weatherList.toTypedArray(),
                        place,
                        1
                    )
                )
            }
        binding.etPlace.setOnEditorActionListener { textView, _, _ ->
            viewLifecycleOwner.lifecycleScope.launch {
                val newPlace = viewModel.getPlaceByName(textView.text.toString())
                val weatherForecastList =
                    viewModel.getForecast(newPlace.latitude, newPlace.longitude)
                val airQuality =
                    viewModel.getAirQualityData(newPlace.latitude, newPlace.longitude)

                binding.rvForecastPreview.adapter =
                    WeatherForecastAdapter(
                        airQuality.quality.score,
                        newPlace,
                        weatherForecastList,
                        requireContext()
                    ) { weatherList, airQuality  ->
                        findNavController().navigate(
                            MainWeatherFragmentDirections.showFullDayForecast(
                                weatherList.toTypedArray(),
                                newPlace,
                                airQuality
                            )
                        )
                    }
                binding.btnMap.setOnClickListener {
                    findNavController().navigate(
                        MainWeatherFragmentDirections.showMap(
                            newPlace.latitude.toFloat(),
                            newPlace.longitude.toFloat()
                        )
                    )
                }
            }
            true
        }
        binding.btnMap.setOnClickListener {
            findNavController().navigate(
                MainWeatherFragmentDirections.showMap(
                    place.latitude.toFloat(),
                    place.longitude.toFloat()
                )
            )
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
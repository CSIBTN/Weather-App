package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.qforecast.databinding.FragmentWeatherBinding
import com.csibtn.qforecast.di.AppModule
import kotlinx.coroutines.launch

class MainWeatherFragment() : Fragment() {
    private val viewModel: WeatherFragmentViewModel =
        WeatherFragmentViewModel(AppModule.getWeatherRepository())
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
        binding.etPlace.setOnEditorActionListener { textView, _, _ ->
            viewLifecycleOwner.lifecycleScope.launch {
                val place = viewModel.getPlaceByName(textView.text.toString())[0]
                val weatherForecastList = viewModel.getForecast(place.latitude, place.longitude)
                binding.rvForecastPreview.adapter =
                    WeatherForecastAdapter(
                        weatherForecastList,
                        place,
                        requireContext()
                    ) { weatherList ->
                        findNavController().navigate(
                            MainWeatherFragmentDirections.showFullDayForecast(
                                weatherList.toTypedArray(),
                                place
                            )
                        )
                    }
                binding.btnMap.setOnClickListener {
                    findNavController().navigate(
                        MainWeatherFragmentDirections.showMap(
                            place.latitude.toFloat(),
                            place.longitude.toFloat()
                        )
                    )
                }
            }
            true
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
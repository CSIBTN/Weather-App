package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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
        binding.etPlace.setOnEditorActionListener { textView, _, _ ->
            viewLifecycleOwner.lifecycleScope.launch {
                val place = viewModel.getPlaceByName(textView.text.toString())[0]
                val weatherForecastSingle = viewModel.getForecast(place.latitude,place.longitude)
            }
            true
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
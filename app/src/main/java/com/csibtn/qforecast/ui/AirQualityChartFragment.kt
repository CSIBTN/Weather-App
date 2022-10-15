package com.csibtn.qforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.csibtn.qforecast.databinding.FragmentAirQualityChartBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartFontWeightType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

class AirQualityChartFragment : Fragment() {
    val args: AirQualityChartFragmentArgs by navArgs()
    var binding: FragmentAirQualityChartBinding? = null
    val _binding: FragmentAirQualityChartBinding
        get() = checkNotNull(binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirQualityChartBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val airQuality = args.airQuality.components
        val chart = binding?.aaChartView
        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Air Quality Components")
            .axesTextColor("#FFFFFF")
            .dataLabelsStyle(AAStyle().fontSize(24).fontWeight(AAChartFontWeightType.Thin))
            .titleStyle(AAStyle().color("#FFFFFF").fontSize(24))
            .backgroundColor("#5171A5")
            .dataLabelsEnabled(true)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Ammonia Concentration")
                        .data(
                            arrayOf(
                                airQuality.ammoniaConcentration
                            )
                        ),
                    AASeriesElement()
                        .name("Carbon Concentration")
                        .data(
                            arrayOf(
                                airQuality.carbonConcentration
                            )
                        ),
                    AASeriesElement()
                        .name("Nitrogen Concentration")
                        .data(
                            arrayOf(
                                airQuality.nitrogenConcentration
                            )
                        )
                )
            )
        chart?.aa_drawChartWithChartModel(aaChartModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
package com.csibtn.qforecast.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csibtn.qforecast.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_item)
    }
}
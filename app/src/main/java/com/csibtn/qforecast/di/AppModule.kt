package com.csibtn.qforecast.di

import com.csibtn.qforecast.data.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getWeatherRepository(): WeatherRepository = WeatherRepository()

}
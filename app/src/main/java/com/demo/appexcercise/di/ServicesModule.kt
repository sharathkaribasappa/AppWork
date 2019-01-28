package com.demo.appexcercise.di

import com.demo.appexcercise.network.HttpClient
import com.demo.appexcercise.repository.CurrentWeatherRepository
import com.demo.appexcercise.repository.CurrentWeatherUseCase
import com.demo.appexcercise.repository.ForecastWeatherRepository
import com.demo.appexcercise.repository.ForecastWeatherUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient()
    }

    @Provides
    @Singleton
    fun provideForecastWeatherRepository(): ForecastWeatherRepository {
        return ForecastWeatherRepository()
    }

    @Provides
    @Singleton
    fun provideForecastWeatherUseCase(forecastWeatherRepository: ForecastWeatherRepository): ForecastWeatherUseCase {
        return ForecastWeatherUseCase(forecastWeatherRepository)
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(): CurrentWeatherRepository {
        return CurrentWeatherRepository()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): CurrentWeatherUseCase {
        return CurrentWeatherUseCase(currentWeatherRepository)
    }
}
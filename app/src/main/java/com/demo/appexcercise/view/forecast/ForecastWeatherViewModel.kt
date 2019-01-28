package com.demo.appexcercise.view.forecast

import com.demo.appexcercise.model.ForecastWeather
import com.demo.appexcercise.repository.ForecastWeatherRepository
import com.demo.appexcercise.repository.ForecastWeatherUseCase
import com.demo.appexcercise.view.BaseViewModel

//view model class for handling ForecastWeather request
class ForecastWeatherViewModel : BaseViewModel<ForecastWeather>() {
    //TODO: Inject these using Dagger
    val forecastWeatherRepository = ForecastWeatherRepository()
    val forecastWeatherUseCase = ForecastWeatherUseCase(forecastWeatherRepository)

    fun load(query: Long) {
        forecastWeatherUseCase(query.toString()) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

}

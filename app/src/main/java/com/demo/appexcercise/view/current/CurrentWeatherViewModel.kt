package com.demo.appexcercise.view.current

import com.demo.appexcercise.model.CurrentWeather
import com.demo.appexcercise.repository.CurrentWeatherRepository
import com.demo.appexcercise.repository.CurrentWeatherUseCase
import com.demo.appexcercise.view.BaseViewModel

//View model class for handling currentweather request
class CurrentWeatherViewModel : BaseViewModel<CurrentWeather>() {
    //TODO: Inject these using Dagger
    var currentWeatherRepository = CurrentWeatherRepository()
    var currentWeatherUseCase  = CurrentWeatherUseCase(currentWeatherRepository)

    fun load(query: String) {
        currentWeatherUseCase(query) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }
}

package com.demo.appexcercise.repository

import com.demo.appexcercise.model.CurrentWeather
import com.demo.appexcercise.network.HttpClient
import com.demo.appexcercise.util.Either

class CurrentWeatherRepository : BaseRepository() {

    //TODO : Inject this object using dagger
    var httpClient  = HttpClient()

    fun services(param: String): Either<Failure, CurrentWeather> {
        return request(httpClient.currentWeatherApi.getCurrentWeather(param))
    }
}
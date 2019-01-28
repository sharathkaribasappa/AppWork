package com.demo.appexcercise.repository

import com.demo.appexcercise.model.ForecastWeather
import com.demo.appexcercise.network.HttpClient
import com.demo.appexcercise.util.Either

class ForecastWeatherRepository: BaseRepository() {
    var httpClient = HttpClient()

    fun services(param: Long): Either<Failure, ForecastWeather> {
        return request(httpClient.forecastWeatherApi.getForecast(param))
    }
}
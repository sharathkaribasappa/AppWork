package com.demo.appexcercise.repository

import com.demo.appexcercise.model.ForecastWeather
import com.demo.appexcercise.util.Either

class ForecastWeatherUseCase constructor(var forecastWeatherRepository: ForecastWeatherRepository) :
    UseCase<ForecastWeather, String>() {
    override suspend fun run(params: String): Either<Failure, ForecastWeather?> {
        return forecastWeatherRepository.services(params.toLong())
    }
}
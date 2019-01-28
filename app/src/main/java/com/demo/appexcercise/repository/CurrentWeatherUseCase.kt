package com.demo.appexcercise.repository

import com.demo.appexcercise.model.CurrentWeather
import com.demo.appexcercise.util.Either

//TODO: Inject the dependency using dagger
class CurrentWeatherUseCase constructor(var currentWeatherRepository: CurrentWeatherRepository) :
    UseCase<CurrentWeather, String>() {
    override suspend fun run(params: String): Either<Failure, CurrentWeather> {
        return currentWeatherRepository.services(params)
    }
}
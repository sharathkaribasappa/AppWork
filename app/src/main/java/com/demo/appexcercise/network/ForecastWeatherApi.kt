package com.demo.appexcercise.network

import com.demo.appexcercise.model.ForecastWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val FORECAST_URL = "data/2.5/forecast"

interface ForecastWeatherApi {

    @GET(FORECAST_URL)
    fun getForecast(@Query("id") id: Long): Call<ForecastWeather>
}
package com.demo.appexcercise.network

import com.demo.appexcercise.model.CurrentWeather
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

const val CURRENTWEATHER_URL = "data/2.5/weather"

interface CurrentWeatherApi {

    @GET(CURRENTWEATHER_URL)
    fun getCurrentWeather(@Query("q") place: String): Call<CurrentWeather>

    @GET(CURRENTWEATHER_URL)
    fun getCurrentWeather(@Query("zip") zipCode: Int): Call<CurrentWeather>
}
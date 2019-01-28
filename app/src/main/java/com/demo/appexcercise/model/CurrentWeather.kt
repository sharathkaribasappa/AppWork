package com.demo.appexcercise.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeather(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    val cod: Int
) : Parcelable

@Parcelize
data class Coordinates(
    val long: Double,
    val lat: Double
) : Parcelable

@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable

@Parcelize
data class Main(
    val temp: Float,
    val pressure: Float,
    val humidity: Float,
    val temp_min: Float,
    val temp_max: Float
) : Parcelable

@Parcelize
data class Wind(
    val speed: Float,
    val deg: Float
) : Parcelable

@Parcelize
data class Clouds(
    val all: Int
) : Parcelable

@Parcelize
data class Sys(
    val type: Int,
    val id: Long,
    val message: Float,
    val country: String,
    val sunrise: Long,
    val sunset: Long
) : Parcelable

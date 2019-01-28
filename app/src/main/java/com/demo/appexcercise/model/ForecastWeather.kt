package com.demo.appexcercise.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastWeather(
    val cod: String,
    val message: Float,
    val cnt: Int,
    val list: List<FCWeather>
) : Parcelable

@Parcelize
data class FCWeather(
    val dt: Long,
    val main: FCMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val rain: Rain,
    val sys: FCSys,
    val dt_txt: String
) : Parcelable

@Parcelize
data class FCMain(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Int,
    val temp_kf: Float
) : Parcelable

@Parcelize
data class Rain(
    @SerializedName("3h")
    val hRain: Float
) : Parcelable

@Parcelize
data class FCSys(val pod: String) : Parcelable

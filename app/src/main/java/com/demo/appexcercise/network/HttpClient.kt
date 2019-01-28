package com.demo.appexcercise.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "990be9b8daf46e33470818b32b57fdbc"

class HttpClient {
    val gson: Gson = Gson()

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("APPID", API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    private val apiClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(apiClient)
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(getGsonFactoryProvider())
        .build()

    fun getGsonFactoryProvider(): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    val currentWeatherApi: CurrentWeatherApi = retrofit().create(CurrentWeatherApi::class.java)

    val forecastWeatherApi: ForecastWeatherApi = retrofit().create(ForecastWeatherApi::class.java)
}
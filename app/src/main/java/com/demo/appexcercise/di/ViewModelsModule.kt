package com.demo.appexcercise.di

import android.arch.lifecycle.ViewModel
import com.demo.appexcercise.view.forecast.ForecastWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastWeatherViewModel:: class)
    internal abstract fun bindForecastWeatherViewModel(model: ForecastWeatherViewModel): ViewModel
}
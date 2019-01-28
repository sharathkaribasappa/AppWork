package com.demo.appexcercise.di

import com.demo.appexcercise.ExcerciseApp
import com.demo.appexcercise.view.forecast.ForecastWeatherViewModel
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ServicesModule::class, AndroidModule::class, ViewModelsModule::class])
interface AppComponent : AndroidInjector<ExcerciseApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ExcerciseApp>()
}
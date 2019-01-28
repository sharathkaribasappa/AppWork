package com.demo.appexcercise.di

import com.demo.appexcercise.view.current.CurrentWeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AndroidModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): CurrentWeatherActivity
}
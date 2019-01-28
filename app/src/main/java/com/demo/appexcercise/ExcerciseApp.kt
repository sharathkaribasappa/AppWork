package com.demo.appexcercise

import com.demo.appexcercise.di.AppComponent
import com.demo.appexcercise.di.DaggerAppComponent
import com.demo.appexcercise.repository.UseCase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.Dispatchers

open class ExcerciseApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out ExcerciseApp> {
        component = DaggerAppComponent.builder().create(this) as AppComponent
        component.inject(this)
        return component
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        UseCase.dispatcher = Dispatchers.IO
    }
}
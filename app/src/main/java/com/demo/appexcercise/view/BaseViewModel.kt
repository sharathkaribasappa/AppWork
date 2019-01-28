package com.demo.appexcercise.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.demo.appexcercise.Resource
import com.demo.appexcercise.model.ForecastWeather
import com.demo.appexcercise.repository.Failure

abstract class BaseViewModel<T> : ViewModel() {
    val liveData: LiveData<Resource<T?>> = MutableLiveData<Resource<T?>>()

    fun handleSuccess(response: T?) {
        (liveData as MutableLiveData).value = Resource(response, null)
    }

    fun handleFailure(failure: Failure) {
        (liveData as MutableLiveData).value = Resource(null, failure)
    }
}
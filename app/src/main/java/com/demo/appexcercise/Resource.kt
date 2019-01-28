package com.demo.appexcercise

import com.demo.appexcercise.repository.Failure

data class Resource<T>(
    val data: T?,
    val message: Failure?
) {
    constructor(failure: Failure): this(null, failure)

    constructor(data: T?) : this(data, null)
}
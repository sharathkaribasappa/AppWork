package com.demo.appexcercise.repository

import com.demo.appexcercise.util.Either
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type : Any, in Params> : CoroutineScope {
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    abstract suspend fun run(params: Params): Either<Failure, Type?>

    operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type?>) -> Unit = {}
    ) {
        if (job.isCancelled) job = Job()
        launch {
            val result = async(dispatcher) {
                run(params)
            }
            onResult(result.await())
        }
    }

    fun cancelAllRequests() = job.cancel()

    companion object {
        lateinit var dispatcher: CoroutineDispatcher
    }
}
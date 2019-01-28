package com.demo.appexcercise.repository

import com.demo.appexcercise.util.Either
import retrofit2.Call
import java.lang.Exception

abstract class BaseRepository {
    fun <T, R> request(call: Call<T>): Either<Failure, R> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                Either.Right(response.body() as R)
            } else {
                Either.Left(Failure("Server error"))
            }
        } catch (e: Exception) {
            Either.Left(Failure(e.message))
        }
    }
}
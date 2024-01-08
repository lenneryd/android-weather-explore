package com.cygni.tim.weatherexplore.data.api.retrofit

import retrofit2.Response


fun <T : Any> Response<T>.toResult(): Result<T> = this.toResult { it }

fun <T : Any, R : Any> Response<T>.toResult(mapping: (T) -> R): Result<R> {
    return if (this.isSuccessful) {
        val value = this.body()
        if (value != null) {
            try {
                Result.success(mapping(value))
            } catch (e: Exception) {
                Result.failure(FailedToMapResponse(e))
            }
        } else {
            Result.failure(NetworkException(this.errorBody()?.toString(), this.code()))
        }
    } else {
        Result.failure(NetworkException(this.errorBody()?.toString(), this.code()))
    }
}

class NetworkException(val errorBody: String?, val errorCode: Int) : Exception()
class NoResponseException() : Exception()
class FailedToMapResponse(source: Exception) : Exception(source)

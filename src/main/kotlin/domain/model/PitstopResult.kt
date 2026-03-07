package com.pitstop.domain.model

sealed class PitstopResult<out T> {
    data class Success<T>(val data: T) : PitstopResult<T>()
    data class Error(val message: String, val cause: Throwable? = null) : PitstopResult<Nothing>()
}
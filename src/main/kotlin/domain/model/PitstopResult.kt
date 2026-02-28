package com.pitstop.domain.model

sealed class PitStopResult<out T> {
    data class Success<T>(val data: T) : PitStopResult<T>()
    data class Error(val message: String, val cause: Throwable? = null) : PitStopResult<Nothing>()
}
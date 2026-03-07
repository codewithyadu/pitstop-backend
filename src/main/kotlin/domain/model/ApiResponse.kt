package com.pitstop.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: ApiError? = null
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(
            success = true,
            data = data
        )

        fun <T> error(code: String, message: String): ApiResponse<T> = ApiResponse(
            success = false,
            error = ApiError(
                code = code,
                message = message
            )
        )
    }
}

@Serializable
data class ApiError(
    val code: String,
    val message: String
)
package com.pitstop.domain.model

object ErrorCode {
    const val SCHEDULE_FETCH_FAILED = "SCHEDULE_FETCH_FAILED"
    const val NEXT_RACE_NOT_FOUND = "NEXT_RACE_NOT_FOUND"
    const val INVALID_SEASON = "INVALID_SEASON"
    const val INTERNAL_ERROR = "INTERNAL_ERROR"
    const val UPSTREAM_UNAVAILABLE = "UPSTREAM_UNAVAILABLE"
}
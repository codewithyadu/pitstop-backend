package com.pitstop.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RaceWeekend(
    val round: Int,
    val totalRounds: Int,
    val raceName: String,
    val circuitId: String,
    val circuitName: String,
    val country: String,
    val locality: String,
    val lat: Double,
    val lng: Double,
    val sessions: List<Session>,
    val isSprintWeekend: Boolean
)

@Serializable
data class Session(
    val type: SessionType,
    val date: String,
    val time: String
)

enum class SessionType {
    PRACTICE_1,
    PRACTICE_2,
    PRACTICE_3,
    SPRINT_QUALIFYING,
    SPRINT,
    QUALIFYING,
    RACE
}

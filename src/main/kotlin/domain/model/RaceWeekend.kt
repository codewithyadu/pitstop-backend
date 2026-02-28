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
    val long: Double,
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
    PRACTISE_1,
    PRACTISE_2,
    PRACTISE_3,
    SPRINT_QUALIFYING,
    SPRINT,
    QUALIFYING,
    RACE
}

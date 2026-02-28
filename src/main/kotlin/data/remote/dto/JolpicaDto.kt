package com.pitstop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JolpicaScheduleResponse(
    @SerialName("MRData") val mrData: MRData
)

@Serializable
data class MRData(
    val total: String,
    @SerialName("RaceTable") val raceTable: RaceTable
)

@Serializable
data class RaceTable(
    val season: String,
    @SerialName("Races") val races: List<RaceDto>
)

@Serializable
data class RaceDto(
    val season: String,
    val round: String,
    val raceName: String,
    val date: String,
    val time: String? = null,
    @SerialName("Circuit") val circuit: CircuitDto,
    @SerialName("FirstPractice") val firstPractice: SessionTimeDto? = null,
    @SerialName("SecondPractice") val secondPractice: SessionTimeDto? = null,
    @SerialName("ThirdPractice") val thirdPractice: SessionTimeDto? = null,
    @SerialName("Qualifying") val qualifying: SessionTimeDto? = null,
    @SerialName("Sprint") val sprint: SessionTimeDto? = null,
    @SerialName("SprintQualifying") val sprintQualifying: SessionTimeDto? = null
)

@Serializable
data class CircuitDto(
    val circuitId: String,
    val circuitName: String,
    @SerialName("Location") val location: LocationDto
)

@Serializable
data class LocationDto(
    val lat: String,
    val long: String,
    val locality: String,
    val country: String
)

@Serializable
data class SessionTimeDto(
    val date: String,
    val time: String? = null
)
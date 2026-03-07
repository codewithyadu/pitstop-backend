package com.pitstop.data.remote.mapper

import com.pitstop.data.remote.dto.RaceDto
import com.pitstop.domain.model.RaceWeekend
import com.pitstop.domain.model.Session
import com.pitstop.domain.model.SessionType

fun RaceDto.toRaceWeekend(totalRounds: Int): RaceWeekend {
    val sessions = buildList {
        firstPractice?.let { value ->
            add(Session(type = SessionType.PRACTICE_1, date = value.date, time = value.time.orEmpty()))
        }
        secondPractice?.let { value ->
            add(Session(type = SessionType.PRACTICE_2, date = value.date, time = value.time.orEmpty()))
        }
        sprintQualifying?.let { value ->
            add(Session(type = SessionType.SPRINT_QUALIFYING, date = value.date, time = value.time.orEmpty()))
        }
        thirdPractice?.let { value ->
            add(Session(type = SessionType.PRACTICE_3, date = value.date, time = value.time.orEmpty()))
        }
        sprint?.let { value ->
            add(Session(type = SessionType.SPRINT, date = value.date, time = value.time.orEmpty()))
        }
        qualifying?.let { value ->
            add(Session(type = SessionType.QUALIFYING, date = value.date, time = value.time.orEmpty()))
        }
        add(Session(type = SessionType.RACE, date = date, time = time.orEmpty()))
    }

    return RaceWeekend(
        round = round.toInt(),
        totalRounds = totalRounds,
        raceName = raceName,
        circuitId = circuit.circuitId,
        circuitName = circuit.circuitName,
        country = circuit.location.country,
        locality = circuit.location.locality,
        lat = circuit.location.lat.toDouble(),
        lng = circuit.location.long.toDouble(),
        sessions = sessions,
        isSprintWeekend = sprint != null
    )
}
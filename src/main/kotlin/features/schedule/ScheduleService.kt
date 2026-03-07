package com.pitstop.features.feature

import com.pitstop.data.remote.JolpicaClient
import com.pitstop.domain.model.PitstopResult
import com.pitstop.domain.model.RaceWeekend
import java.time.LocalDate
import java.time.ZoneOffset

class ScheduleService(private val jolpicaClient: JolpicaClient) {

    suspend fun getFullSchedule(season: String = "2026"): PitstopResult<List<RaceWeekend>> {
        return jolpicaClient.getSeasonSchedule(season = season)
    }

    suspend fun getNextRace(season: String = "2026"): PitstopResult<RaceWeekend> {
        return when (val result = jolpicaClient.getSeasonSchedule(season = season)) {
            is PitstopResult.Success -> {
                val today = LocalDate.now(ZoneOffset.UTC).toString()
                val nextRace = result.data.firstOrNull { race ->
                    race.sessions.any { session -> session.date >= today }
                }

                if (nextRace != null) {
                    PitstopResult.Success(nextRace)
                } else {
                    PitstopResult.Error("No upcoming race found for the season $season")
                }
            }

            is PitstopResult.Error -> {
                result
            }
        }
    }
}
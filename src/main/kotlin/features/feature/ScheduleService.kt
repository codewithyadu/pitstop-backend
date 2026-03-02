package com.pitstop.features.feature

import com.pitstop.data.remote.JolpicaClient
import com.pitstop.domain.model.PitStopResult
import com.pitstop.domain.model.RaceWeekend
import java.time.LocalDate

class ScheduleService(private val jolpicaClient: JolpicaClient) {

    suspend fun getFullSchedule(season: String = "2026"): PitStopResult<List<RaceWeekend>> {
        return jolpicaClient.getSeasonSchedule(season = season)
    }

    suspend fun getNextRace(season: String = "2026"): PitStopResult<RaceWeekend> {
        return when (val result = jolpicaClient.getSeasonSchedule(season = season)) {
            is PitStopResult.Success -> {
                val today = LocalDate.now().toString()
                val nextRace = result.data.firstOrNull { race ->
                    race.sessions.any { session -> session.date >= today }
                }

                if (nextRace != null) {
                    PitStopResult.Success(nextRace)
                } else {
                    PitStopResult.Error("No upcoming race found for the season $season")
                }
            }

            is PitStopResult.Error -> {
                result
            }
        }
    }
}
package com.pitstop.data.remote

import com.pitstop.data.remote.dto.JolpicaScheduleResponse
import com.pitstop.data.remote.mapper.toRaceWeekend
import com.pitstop.domain.model.ErrorCode
import com.pitstop.domain.model.PitstopResult
import com.pitstop.domain.model.RaceWeekend
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.slf4j.LoggerFactory

class JolpicaClient(private val httpClient: HttpClient) {

    private val logger = LoggerFactory.getLogger(JolpicaClient::class.java)

    companion object {
        private const val BASE_URL = "https://api.jolpi.ca/ergast/f1"
        private const val RACES_END_POINT = "/races/"
    }

    suspend fun getSeasonSchedule(season: String = "2026"): PitstopResult<List<RaceWeekend>> {
        return try {
            val response = httpClient
                .get("$BASE_URL/$season$RACES_END_POINT")
                .body<JolpicaScheduleResponse>()

            val races = response.mrData.raceTable.races

            val raceWeekends = races.map { race ->
                race.toRaceWeekend(totalRounds = races.size)
            }

            PitstopResult.Success(data = raceWeekends)
        } catch (e: Exception) {
            logger.error("Failed to fetch season schedule for $season", e)
            PitstopResult.Error(
                message = "Failed to fetch schedule from Jolpica",
                code = ErrorCode.SCHEDULE_FETCH_FAILED,
                cause = e
            )
        }
    }
}
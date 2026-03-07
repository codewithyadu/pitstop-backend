package com.pitstop.features.feature

import com.pitstop.domain.model.PitstopResult
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureScheduleRoutes(service: ScheduleService) {
    routing {
        route("/v1") {

            get("/schedule") {
                val season = call.request.queryParameters["season"] ?: "2026"
                when (val result = service.getFullSchedule(season)) {
                    is PitstopResult.Success -> call.respond(result.data)
                    is PitstopResult.Error -> call.respond(
                        HttpStatusCode.InternalServerError,
                        mapOf("error" to result.message)
                    )
                }
            }

            get("/schedule/next") {
                when (val result = service.getNextRace()) {
                    is PitstopResult.Success -> call.respond(result.data)
                    is PitstopResult.Error -> call.respond(
                        HttpStatusCode.NotFound,
                        mapOf("error" to result.message)
                    )
                }
            }
        }
    }
}
package com.pitstop

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/health") {
            call.respondText("Pitstop backend is running")
        }

        route("/v1") {
            get("/status") {
                call.respond(
                    mapOf(
                        "status" to "ok",
                        "service" to "pitstop-api",
                        "version" to "0.1.0"
                    )
                )
            }
        }
    }
}

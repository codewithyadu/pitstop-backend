package com.pitstop

import com.pitstop.data.remote.JolpicaClient
import com.pitstop.features.feature.ScheduleService
import com.pitstop.features.feature.configureScheduleRoutes
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }


    configureMonitoring()
    configureSerialization()
    configureRouting()

    // Schedule
    val jolpicaClient = JolpicaClient(httpClient)
    val scheduleService = ScheduleService(jolpicaClient)
    configureScheduleRoutes(scheduleService)
}

package com.poulastaa

import com.poulastaa.data.model.User
import com.poulastaa.plugins.configureRouting
import com.poulastaa.plugins.configureSerialization
import io.ktor.server.application.*
import java.util.concurrent.ConcurrentHashMap

val users = ConcurrentHashMap<String, User>()

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}

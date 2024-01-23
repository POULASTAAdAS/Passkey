package com.poulastaa

import com.poulastaa.data.model.CreatePasskeyResponse
import com.poulastaa.data.model.Ids
import com.poulastaa.data.model.User
import com.poulastaa.plugins.*
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

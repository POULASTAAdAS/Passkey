package com.poulastaa.plugins

import com.poulastaa.routes.createUser
import com.poulastaa.routes.getUser
import com.poulastaa.routes.passkeyRequest
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        passkeyRequest()
        createUser()
        getUser()

        staticFiles(
            remotePath = ".well-known",
            dir = File("certs")
        )
    }
}

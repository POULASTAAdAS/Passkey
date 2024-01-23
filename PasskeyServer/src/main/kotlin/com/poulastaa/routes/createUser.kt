package com.poulastaa.routes

import com.poulastaa.data.model.Endpoints
import com.poulastaa.data.model.User
import com.poulastaa.data.model.UserCreationResponse
import com.poulastaa.users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createUser() {
    route(Endpoints.CreateUser.route) {
        post {
            val user = call.receiveNullable<User>() ?: return@post call.respond(
                message = "null",
                status = HttpStatusCode.BadRequest
            )

            // not checking if user exists cause it should happen on passkeyRequest route
            try {
                users[user.email] = user
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")
                println("${user.publicKey}\n")

                call.respond(
                    message = UserCreationResponse(
                        status = true
                    ),
                    status = HttpStatusCode.OK
                )
            } catch (e: Exception) {
                call.respond(
                    message = UserCreationResponse(
                        status = false
                    ),
                    status = HttpStatusCode.Conflict
                )
            }
        }
    }
}
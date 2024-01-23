package com.poulastaa.routes

import com.poulastaa.data.model.Endpoints
import com.poulastaa.data.model.GetUserRequest
import com.poulastaa.data.model.User
import com.poulastaa.users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUser() {
    route(Endpoints.GetUser.route) {
        post {
            val userRequest = call.receiveNullable<GetUserRequest>() ?: return@post call.respond(
                message = "invalid Request",
                status = HttpStatusCode.BadRequest
            )

            try {
                users.values.forEach {
                    if (it.id == userRequest.id) {
                        call.respond(
                            message = it,
                            status = HttpStatusCode.OK
                        )

                        return@post
                    }
                }

                call.respond(
                    message = "no user",
                    status = HttpStatusCode.BadRequest
                )
            } catch (e: Exception) {
                call.respond(
                    message = "no user",
                    status = HttpStatusCode.BadRequest
                )
            }
        }
    }
}
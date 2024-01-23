package com.poulastaa.routes

import com.poulastaa.data.model.*
import com.poulastaa.users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.passkeyRequest() {
    route(Endpoints.PasskeyReq.route) {
        post {
            val userInfo = call.receiveNullable<UserInfo>() ?: return@post call.respond(
                message = "Invalid Request",
                status = HttpStatusCode.BadRequest
            )

            if (users.keys().toList().contains(userInfo.email)) {
                call.respond(
                    message = GetPasskeyResponse(
                        allowCredentials = listOf(
                            GetPasskeyResponse.AllowCredentials(
                                id = users[userInfo.email]!!.id,
                                transports = listOf(),
                                type = "public-key"
                            )
                        )
                    ),
                    status = HttpStatusCode.OK
                )

                return@post
            }

            call.respond(
                message = CreatePasskeyResponse(
                    user = CreatePasskeyResponse.User(
                        name = userInfo.email,
                        displayName = userInfo.displayName
                    )
                ),
                status = HttpStatusCode.OK
            )
        }
    }
}
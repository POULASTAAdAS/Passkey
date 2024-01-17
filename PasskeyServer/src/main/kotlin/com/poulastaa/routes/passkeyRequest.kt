package com.poulastaa.routes

import com.poulastaa.data.model.Endpoints
import com.poulastaa.data.model.PasskeyResponse
import com.poulastaa.data.model.User
import com.poulastaa.data.model.UserInfo
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


            call.respond(
                message = PasskeyResponse(
                    user = User(
                        name = userInfo.email,
                        displayName = userInfo.userName
                    )
                ),
                status = HttpStatusCode.OK
            )
        }
    }
}
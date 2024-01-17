package com.poulastaa.data.model

sealed class Endpoints(val route: String) {
    data object PasskeyReq : Endpoints(route = "/api/auth")
}
package com.poulastaa.data.model

sealed class Endpoints(val route: String) {
    data object PasskeyReq : Endpoints(route = "/api/auth")
    data object CreateUser : Endpoints(route = "/api/createUser")
    data object GetUser : Endpoints(route = "/api/getUser")
}
package com.poulastaa.passekyapp.navigation

sealed class Screens(val route: String) {
    data object AuthScreen : Screens(route = "/auth")
    data object Home : Screens(route = "/home")
}
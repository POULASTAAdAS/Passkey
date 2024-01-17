package com.poulastaa.passekyapp.data.model

sealed class AuthState {
    data object Default : AuthState()

    data object SignUpSuccess : AuthState()
    data class SignUpFailure(val message: String) : AuthState()

    data object NotValidEmail : AuthState()

    data object SignInSuccess : AuthState()
    data class SignInFailure(val message: String) : AuthState()
}
package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val email: String,
    val userName: String
)

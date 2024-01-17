package com.poulastaa.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val email: String,
    val userName: String
)

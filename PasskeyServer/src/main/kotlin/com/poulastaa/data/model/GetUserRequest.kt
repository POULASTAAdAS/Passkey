package com.poulastaa.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetUserRequest(
    val id: String
)


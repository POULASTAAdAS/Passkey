package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetUserRequest(
    val id: String
)

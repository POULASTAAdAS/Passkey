package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String
)

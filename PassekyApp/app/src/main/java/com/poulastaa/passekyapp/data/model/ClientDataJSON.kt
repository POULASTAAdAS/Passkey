package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientDataJSON(
    val type: String,
    val challenge: String,
    val origin: String,
    val crossOrigin: Boolean,
    val authenticatorName: String
)

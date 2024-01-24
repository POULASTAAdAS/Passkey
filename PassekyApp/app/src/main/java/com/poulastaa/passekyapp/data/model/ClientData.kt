package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientData(
    val type: String,
    val challenge: String,
    val origin: String,
    val androidPackageName: String
)

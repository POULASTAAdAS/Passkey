package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePasskeyResponseData(
    val id: String,
    val type: String,
    val rawId: String,
    val response: Response,
)

@Serializable
data class Response(
    val clientDataJSON: String,
    val attestationObject: String,
)
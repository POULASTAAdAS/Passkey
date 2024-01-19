package com.poulastaa.passekyapp.data.model

data class PasskeyResponse(
    val id: String,
    val rawId: String,
    val type: String,
    val authenticatorAttachment: String,
    val clientDataJSON: String,
    val attestationObject: String
)

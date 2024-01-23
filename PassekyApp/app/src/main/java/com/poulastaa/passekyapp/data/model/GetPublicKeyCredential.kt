package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetPublicKeyCredential(
    val rawId: String,
    val authenticatorAttachment: String,
    val type: String,
    val id: String,
    val response: Response
) {
    @Serializable
    data class Response(
        val clientDataJSON: String,
        val authenticatorData: String,
        val signature: String,
        val userHandle: String
    )
}
package com.poulastaa.passekyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PublicKeyCredentialResponse(
    val rawId: String,
    val authenticatorAttachment: String,
    val type: String,
    val id: String,
    val response: Response,
) {
    @Serializable
    data class Response(
        val clientDataJSON: String,
        val attestationObject: String,
        val authenticatorData: String,
        val publicKeyAlgorithm: Int,
        val publicKey: String
    )
}


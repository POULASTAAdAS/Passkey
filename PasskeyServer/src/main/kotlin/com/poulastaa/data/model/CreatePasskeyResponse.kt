package com.poulastaa.data.model

import com.poulastaa.utils.Constants.BASE_URL
import com.poulastaa.utils.generateFidoChallenge
import kotlinx.serialization.Serializable
import java.security.SecureRandom
import java.util.*

@Serializable
data class CreatePasskeyResponse(
    val challenge: String = generateFidoChallenge(),
    val rp: Rp = Rp(),
    val user: User,
    val pubKeyCredParams: List<PubKeyCredParams> = listOf(
        PubKeyCredParams()
    ),
    val timeout: Long = 1800000,
    val attestation: String = "none",
    val excludeCredentials: List<ExcludeCredentials> = emptyList(),
    val authenticatorSelection: AuthenticatorSelection = AuthenticatorSelection()
): BaseModel(type = "SignUp") {
    @Serializable
    data class Rp(
        val name: String = "PassekyApp",
        val id: String = BASE_URL.removePrefix("https://")
    )

    @Serializable
    data class User(
        val id: String = UUID.randomUUID().toString(),
        val name: String,// = "poulastaadas2@gmail.com",
        val displayName: String,// = name.removeSuffix("@gmail.com")
    )

    @Serializable
    data class PubKeyCredParams(
        val type: String = "public-key",
        val alg: Int = -7
    )

    @Serializable
    data class ExcludeCredentials(
        val id: String,
        val type: String
    )

    @Serializable
    data class AuthenticatorSelection(
        val authenticatorAttachment: String = "platform",
        val requireResidentKey: Boolean = false,
        val residentKey: String = "required",
    )
}
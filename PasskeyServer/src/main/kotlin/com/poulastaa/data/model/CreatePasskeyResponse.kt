package com.poulastaa.data.model

import com.poulastaa.utils.Constants.BASE_URL
import com.poulastaa.utils.generateFidoChallenge
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CreatePasskeyResponse(
    val challenge: String = generateFidoChallenge(),
    val rp: Rp = Rp(),
    val user: User,
    val pubKeyCredParams: List<PubKeyCredParams> = listOf(
        PubKeyCredParams(
            type = "public-key",
            alg = -7
        ),
        PubKeyCredParams(
            type = "public - key",
            alg = -257
        )
    ),
    val timeout: Long = 1800000,
    val attestation: String = "none",
    val excludeCredentials: List<ExcludeCredentials> = emptyList(),
    val authenticatorSelection: AuthenticatorSelection = AuthenticatorSelection(),
) : BaseModel(type = "SignUp") {
    @Serializable
    data class Rp(
        val name: String = "PassekyApp",
        val id: String = BASE_URL.removePrefix("https://"),
    )

    @Serializable
    data class User(
        val id: String = UUID.randomUUID().toString(),
        val name: String,// = "poulastaadas2@gmail.com",
        val displayName: String,// = name.removeSuffix("@gmail.com")
    )

    @Serializable
    data class PubKeyCredParams(
        val type: String,
        val alg: Int,
    )

    @Serializable
    data class ExcludeCredentials(
        val id: String,
        val type: String,
    )

    @Serializable
    data class AuthenticatorSelection(
        val authenticatorAttachment: String = "platform",
        val requireResidentKey: Boolean = true,
        val residentKey: String = "required",
        val userVerification: String = "required",
    )
}
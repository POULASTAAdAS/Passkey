package com.poulastaa.data.model

import com.poulastaa.utils.Constants
import com.poulastaa.utils.generateFidoChallenge
import kotlinx.serialization.Serializable

@Serializable
data class GetPasskeyResponse(
    val challenge: String = generateFidoChallenge(),
    val allowCredentials: List<AllowCredentials>,
    val timeout: Long = 1800000,
    val userVerification: String = "required",
    val rpId: String = Constants.BASE_URL.removePrefix("https://")
) : BaseModel(type = "LogIn") {
    @Serializable
    data class AllowCredentials(
        val id: String,
        val transports: List<String>,
        val type: String,
    )
}
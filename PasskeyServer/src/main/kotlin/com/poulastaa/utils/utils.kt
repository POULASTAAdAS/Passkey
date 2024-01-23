package com.poulastaa.utils

import java.security.SecureRandom
import java.util.*

fun generateFidoChallenge(): String {
    val secureRandom = SecureRandom()
    val challengeBytes = ByteArray(64)
    secureRandom.nextBytes(challengeBytes)
    return challengeBytes.b64Encode()
}

fun ByteArray.b64Encode(): String {
    return Base64.getUrlEncoder().encodeToString(this)
}
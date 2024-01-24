package com.poulastaa.passekyapp.utils

import android.util.Log
import android.util.Patterns
import com.poulastaa.passekyapp.data.model.CreatePublicKeyCredentialResponse
import com.poulastaa.passekyapp.data.model.GetPublicKeyCredential
import com.upokecenter.cbor.CBORObject
import java.math.BigInteger
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.PublicKey
import java.security.Signature
import java.security.spec.ECFieldFp
import java.security.spec.ECParameterSpec
import java.security.spec.ECPoint
import java.security.spec.ECPublicKeySpec
import java.security.spec.EllipticCurve
import java.util.Base64

fun String.b64Decode(): ByteArray {
    return Base64.getUrlDecoder().decode(this)
}

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

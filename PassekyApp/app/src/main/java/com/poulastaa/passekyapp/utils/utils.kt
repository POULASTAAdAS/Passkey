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

fun ByteArray.b64Encode(): String {
    return Base64.getUrlEncoder().encodeToString(this)
}

fun CreatePublicKeyCredentialResponse.Response.getPublicKey(): String {
    Log.d("attestationObject", this.attestationObject)
    val attestationByteArray = this.attestationObject.b64Decode()

    val attestationObject = CBORObject.DecodeFromBytes(attestationByteArray)
    val attStmt =
        attestationObject["authData"].toString().substring(2).removeSuffix("'")
    val byteArray = attStmt.chunked(2).map { it.toInt(16).toByte() }.toByteArray()

    return byteArray.b64Encode()
}

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun String.publicKey(): PublicKey {
    val byteArray = this.b64Decode()

    val hash = byteArray.copyOfRange(0, 32)
    val publicKeyByteArray = byteArray.copyOfRange(32, 64)

    val ecPoint = ECPoint(BigInteger(1, hash), BigInteger(1, publicKeyByteArray))
    val ecParameterSpec = EllipticCurves.secp256k1().curve

    val ecKeySpec = ECPublicKeySpec(ecPoint, ecParameterSpec)
    val keyFactory = KeyFactory.getInstance("EC")

    val publicKey =  keyFactory.generatePublic(ecKeySpec)

    Log.d("dhwaoiuedjawihdowa" , publicKey.toString())

    return publicKey
}


fun GetPublicKeyCredential.verifyIdSignature(publicKey: PublicKey): Boolean {
    val signature = this.response.signature.b64Decode()
    val sig = Signature.getInstance("SHA256withECDSA")
    sig.initVerify(publicKey)

    val md = MessageDigest.getInstance("SHA-256")
    val clientDataHash = md.digest(this.response.clientDataJSON.b64Decode())
    val signatureBase = this.response.authenticatorData.b64Decode() + clientDataHash
    sig.update(signatureBase)

    return sig.verify(signature)
}

class EllipticCurves {
    companion object {
        fun secp256k1(): ECParams {
            val p =
                BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16)
            val a = BigInteger.ZERO
            val b = BigInteger("7")
            val n =
                BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16)
            val gX =
                BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16)
            val gY =
                BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16)

            val curveParams =
                ECParameterSpec(EllipticCurve(ECFieldFp(p), a, b), ECPoint(gX, gY), n, 1)
            return ECParams(curveParams)
        }
    }

    data class ECParams(val curve: ECParameterSpec)
}
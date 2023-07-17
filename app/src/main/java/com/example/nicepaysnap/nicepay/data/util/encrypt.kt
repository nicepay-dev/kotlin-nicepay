package com.example.nicepaysnap.nicepay.data.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.apache.commons.codec.binary.Hex
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object encrypt {
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun signSHA256RSA(stringTosign: String, privateKey: String): String? {
        var s: ByteArray? = null
        var sign = ""
        try {
            val b1: ByteArray = Base64.getDecoder().decode(privateKey)
            //			byte[] b1 = Base64.getD
            val kf: KeyFactory = KeyFactory.getInstance("RSA")
            val spec = PKCS8EncodedKeySpec(b1)
            val privateSignature: Signature = Signature.getInstance("SHA256withRSA")
            privateSignature.initSign(kf.generatePrivate(spec))
            privateSignature.update(stringTosign.toByteArray(StandardCharsets.UTF_8))
            s = privateSignature.sign()
            //			hex = Hex.encodeHexString(s);
            sign = Base64.getEncoder().encodeToString(s)
            Log.e("Generate Signature Base64 =", sign)
        } catch (e: Exception) {
            Log.e("Error Generate Signature = ", e.message.toString())
        }
        return sign
    }

    @Throws(java.lang.Exception::class)
    fun sha256EncodeHex(data: String): String? {
        val sh: MessageDigest = MessageDigest.getInstance("SHA-256")
        return Hex.encodeHexString(sh.digest(data.toByteArray(charset("UTF-8"))))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(java.lang.Exception::class)
    fun hmacSha512encodeBase64(key: String, data: String): String? {
        val sha256_HMAC: Mac = Mac.getInstance("HmacSHA512")
        val secret_key = SecretKeySpec(key.toByteArray(), "HmacSHA512")
        sha256_HMAC.init(secret_key)
        val strbyte: ByteArray = sha256_HMAC.doFinal(data.toByteArray(charset("UTF-8")))
        //		  byte[] hex = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(strbyte)
//		  return Hex.encodeHexString(hex);
    }
}
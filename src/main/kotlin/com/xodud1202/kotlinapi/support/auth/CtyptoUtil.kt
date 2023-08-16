package com.xodud1202.kotlinapi.support.auth

import org.apache.commons.codec.binary.Hex
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

object CryptoUtil {
    fun encryptSha256(rawPassword: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-256")
            md.reset()
            md.update(rawPassword.toByteArray())
            String.format("%064x", BigInteger(1, md.digest()))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }

    fun encryptSha512(rawPassword: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-512")
            md.reset()
            md.update(rawPassword.toByteArray())
            String.format("%0128x", BigInteger(1, md.digest()))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }

    @Throws(Exception::class)
    fun encryptAES(rawValue: String): String {
        var encryptedValue = ""
        if (rawValue.isNotEmpty()) {
            val skeySpec = SecretKeySpec(getRawKey(), "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
            val encrypted = cipher.doFinal(rawValue.toByteArray())
            encryptedValue = Hex.encodeHexString(encrypted)
        }
        return encryptedValue
    }

    @Throws(Exception::class)
    fun decryptAES(rawValue: String): String {
        var decryptedValue = ""
        if (rawValue.isNotEmpty()) {
            val skeySpec = SecretKeySpec(getRawKey(), "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec)
            val decrypted = cipher.doFinal(Hex.decodeHex(rawValue.toCharArray()))
            decryptedValue = String(decrypted)
        }
        return decryptedValue
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun getRawKey(): ByteArray {
        val aesKey = "r^r^ApfdlA^semsd^A@h@h^0l"
        val kgen = KeyGenerator.getInstance("AES")
        val sr = SecureRandom.getInstance("SHA1PRNG")
        val seed = aesKey.toByteArray()
        sr.setSeed(seed)
        kgen.init(128, sr)
        val skey = kgen.generateKey()
        return skey.encoded
    }

    fun encryptMD5(rawValue: String): String {
        return try {
            val md = MessageDigest.getInstance("MD5")
            md.update(rawValue.toByteArray())
            val b = md.digest()
            val sb = StringBuilder()
            for (i in b.indices) {
                sb.append(Integer.toString((b[i].toInt() and 255) + 256, 16).substring(1))
            }
            sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return ""
        }
    }
}

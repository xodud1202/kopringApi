package com.xodud1202.kotlinapi.support.auth

/*@Component
class PasswordEncoder : PasswordEncoder {
    private val log: Logger = LoggerFactory.getLogger(PasswordEncoder::class.java)

    override fun encode(rawPassword: CharSequence): String {
        return CryptoUtil.encryptSha512(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return if (encodedPassword.isNotEmpty()) {
            encodedPassword == CryptoUtil.encryptSha512(rawPassword.toString())
        } else {
            log.warn("Empty encoded password")
            false
        }
    }

    fun encodeSha256(rawPassword: CharSequence): String {
        return CryptoUtil.encryptSha256(rawPassword.toString())
    }

    fun matchesSha256(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return if (encodedPassword.isNotEmpty()) {
            encodedPassword == CryptoUtil.encryptSha256(rawPassword.toString())
        } else {
            log.warn("Empty encoded password")
            false
        }
    }

    fun encodeMd5AndSha256(rawPassword: CharSequence): String {
        return CryptoUtil.encryptSha256(CryptoUtil.encryptMD5(rawPassword.toString()))
    }

    fun matchesMd5AndSha256(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return if (encodedPassword.isNotEmpty()) {
            encodedPassword == CryptoUtil.encryptSha256(CryptoUtil.encryptMD5(rawPassword.toString()))
        } else {
            log.warn("Empty encoded password")
            false
        }
    }

    @Throws(Exception::class)
    fun encryptAES(rawValue: String): String {
        return CryptoUtil.encryptAES(rawValue)
    }

    @Throws(Exception::class)
    fun decryptAES(rawValue: String): String {
        return CryptoUtil.decryptAES(rawValue)
    }
}*/

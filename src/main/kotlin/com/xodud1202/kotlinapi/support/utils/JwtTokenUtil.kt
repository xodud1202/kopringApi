package com.xodud1202.kotlinapi.support.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    fun getUsernameFromToken(token: String?): String? {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    fun getExpirationDateFromToken(token: String?): Date? {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: (Claims) -> T): T? {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
	}

	private fun isTokenExpired(token: String?): Boolean {
		val expiration = getExpirationDateFromToken(token)
		return expiration?.before(Date()) ?: true
	}

	fun generateToken(userDetails: UserDetails): String {
		val claims = HashMap<String, Any>()
		return doGenerateToken(claims, userDetails.username)
	}

	private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
		val createdDate = Date()
		val expirationDate = Date(createdDate.time + expiration * 1000)

		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(createdDate)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact()
	}

	fun validateToken(token: String?, userDetails: UserDetails): Boolean {
		val username = getUsernameFromToken(token)
		return username == userDetails.username && !isTokenExpired(token)
	}

}

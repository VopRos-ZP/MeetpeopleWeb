package com.meetpeople.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class JWTTokenUtils {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.lifetime}")
    private lateinit var lifetime: Duration

    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        val issuedDate = Date()
        val expiredDate = Date(issuedDate.time + lifetime.toMillis())
        return Jwts.builder()
            .claims(claims)
            .subject(userDetails.username)
            .issuedAt(issuedDate)
            .expiration(expiredDate)
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .compact()
    }

    fun username(token: String): String = getAllClaims(token).subject

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parse(token)
            .payload as Claims
    }

}
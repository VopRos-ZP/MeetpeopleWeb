package com.meetpeople.request

data class JwtRequest(
    val phone: String,
    val password: String
)
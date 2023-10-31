package com.meetpeople.response

data class JwtEntityResponse<T>(
    val token: String,
    val result: T
)

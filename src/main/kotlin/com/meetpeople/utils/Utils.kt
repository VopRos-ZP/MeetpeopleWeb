package com.meetpeople.utils

object Utils {
    fun fetchToken(token: String): String = token.split(" ")[1]
}
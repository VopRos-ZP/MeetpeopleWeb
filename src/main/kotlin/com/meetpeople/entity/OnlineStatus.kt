package com.meetpeople.entity

enum class OnlineStatus {
    OFFLINE, ONLINE;
    override fun toString(): String = name.lowercase()
}
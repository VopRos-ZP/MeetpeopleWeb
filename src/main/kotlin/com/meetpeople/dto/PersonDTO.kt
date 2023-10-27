package com.meetpeople.dto

data class PersonDTO(
    val firstname: String,
    val lastname: String,
    val password: String,
    val phone: String,
    val gender: String,
    val birthday: Long,
    val locationId: Long,
    val maritalStatus: String,
    val status: String,
    val about: String,
    val premium: Boolean,
    val onlineStatus: String,
    val sessions: Set<Long>,
    val possibleMeetings: Set<Long>,
    val meetings: Set<Long>
)
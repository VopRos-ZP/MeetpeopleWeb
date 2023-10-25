package com.meetpeople.dto

data class MeetingDTO(
    val name: String,
    val description: String,
    val locationId: Long,
    val date: Long?, // null - потом договоримся
    val views: Long,
    val publicationDate: Long,
    val personId: Long,
    val possibleParticipants: Set<Long>,
    val participants: Set<Long>,
    val tags: Set<Long>,
    val desiredCompanion: String
)

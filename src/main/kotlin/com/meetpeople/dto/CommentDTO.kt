package com.meetpeople.dto

data class CommentDTO(
    val text: String,
    val author: Long,
    val meeting: Long,
    val date: Long,
    val isEdited: Boolean,
    val reply: Long
)

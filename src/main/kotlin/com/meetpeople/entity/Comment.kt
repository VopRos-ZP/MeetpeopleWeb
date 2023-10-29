package com.meetpeople.entity

import jakarta.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val text: String,
    @ManyToOne
    @JoinColumn(name = "person_id")
    val author: Person,
    @ManyToOne
    @JoinColumn(name = "meeting_id")
    val meeting: Meeting,
    val date: Long,
    @Column(name = "is_edited")
    val isEdited: Boolean,
    @ManyToOne
    @JoinColumn(name = "reply_id", nullable = true)
    val reply: Comment?
)

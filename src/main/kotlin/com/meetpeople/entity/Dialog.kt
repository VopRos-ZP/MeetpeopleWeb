package com.meetpeople.entity

import jakarta.persistence.*

@Entity
@Table(name = "dialogs")
data class Dialog(
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
    @JoinColumn(name = "dialog_id", nullable = true)
    val reply: Dialog?
)

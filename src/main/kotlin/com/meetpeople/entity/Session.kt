package com.meetpeople.entity

import jakarta.persistence.*

@Entity
@Table(name = "sessions")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "login_date")
    val loginDate: Long,
    @Column(name = "ip_address")
    val ipAddress: String
)

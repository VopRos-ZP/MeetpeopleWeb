package com.meetpeople.entity

import jakarta.persistence.*

@Entity
@Table(name = "locations")
data class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val country: String,
    val city: String,
    val coordinates: String // example: 54.55003 38.995432
)

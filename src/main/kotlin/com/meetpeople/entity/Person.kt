package com.meetpeople.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "persons")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val firstname: String,
    val lastname: String,
    val password: String,
    val phone: String,
    val gender: String,
    val birthday: Long,

    @ManyToOne
    @JoinColumn(name = "location_id")
    val location: Location,

    @Column(name = "marital_status")
    val maritalStatus: String,
    val status: String,
    val about: String,
    val premium: Boolean,

    @ManyToMany(mappedBy = "possibleParticipants")
    @JsonIgnore
    val possibleMeetings: Set<Meeting>,

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    val meetings: Set<Meeting>
)

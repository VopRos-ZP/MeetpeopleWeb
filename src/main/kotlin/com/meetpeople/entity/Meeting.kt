package com.meetpeople.entity

import jakarta.persistence.*

@Entity
@Table(name = "meetings")
data class Meeting(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val description: String,

    @ManyToOne
    @JoinColumn(name = "location_id")
    val location: Location,
    val date: Long?, // null - потом договоримся
    val views: Long,
    val publicationDate: Long,

    @ManyToOne
    @JoinColumn(name = "person_id")
    val personId: Person,

    @ManyToMany
    @JoinTable(
        name = "possible_participants",
        joinColumns = [JoinColumn(name = "meeting_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")]
    )
    val possibleParticipants: Set<Person>,

    @ManyToMany
    @JoinTable(
        name = "participants",
        joinColumns = [JoinColumn(name = "meeting_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")]
    )
    val participants: Set<Person>,

    @ManyToMany
    @JoinTable(
        name = "meeting_tags",
        joinColumns = [JoinColumn(name = "meeting_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "id")]
    )
    val tags: Set<Tag>,
    val desiredCompanion: String
)

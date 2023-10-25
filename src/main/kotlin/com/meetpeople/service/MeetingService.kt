package com.meetpeople.service

import com.meetpeople.dto.MeetingDTO
import com.meetpeople.entity.Meeting
import com.meetpeople.repository.LocationRepository
import com.meetpeople.repository.MeetingRepository
import com.meetpeople.repository.PersonRepository
import com.meetpeople.repository.TagRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MeetingService(
    private val tagRepository: TagRepository,
    private val personRepository: PersonRepository,
    private val meetingRepository: MeetingRepository,
    private val locationRepository: LocationRepository,
) {

    fun getAll(): List<Meeting> = meetingRepository.findAll()

    fun getById(id: Long): Optional<Meeting> = meetingRepository.findById(id)

    fun save(dto: MeetingDTO): Meeting = fromDTO(
        (getAll().size + 1).toLong(), dto
    )

    fun update(id: Long, dto: MeetingDTO): Meeting = fromDTO(id, dto)

    fun delete(id: Long) {
        meetingRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: MeetingDTO): Meeting = meetingRepository.save(Meeting(
        id = id,
        name = dto.name,
        description = dto.description,
        location = locationRepository.findById(dto.locationId).get(),
        date = dto.date,
        views = dto.views,
        publicationDate = dto.publicationDate,
        personId = personRepository.findById(dto.personId).get(),
        possibleParticipants = personRepository.findAllById(dto.possibleParticipants).toSet(),
        participants = personRepository.findAllById(dto.participants).toSet(),
        tags = tagRepository.findAllById(dto.tags).toSet(),
        desiredCompanion = dto.desiredCompanion
    ))

}
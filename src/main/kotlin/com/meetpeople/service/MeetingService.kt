package com.meetpeople.service

import com.meetpeople.dto.MeetingDTO
import com.meetpeople.entity.Meeting
import com.meetpeople.repository.LocationRepository
import com.meetpeople.repository.MeetingRepository
import com.meetpeople.repository.PersonRepository
import com.meetpeople.repository.TagRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.Optional

@Qualifier
@Service
class MeetingService(
    private val tagRepository: TagRepository,
    private val personRepository: PersonRepository,
    private val meetingRepository: MeetingRepository,
    private val locationRepository: LocationRepository,
) : EntityService<Meeting, MeetingDTO> {

    override fun fetchAll(): List<Meeting> = meetingRepository.findAll()

    override fun fetchById(id: Long): Optional<Meeting> = meetingRepository.findById(id)

    override fun create(dto: MeetingDTO): Meeting = fromDTO(
        (fetchAll().size + 1).toLong(), dto
    )

    override fun update(id: Long, dto: MeetingDTO): Meeting = fromDTO(id, dto)

    override fun delete(id: Long) {
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
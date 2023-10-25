package com.meetpeople.service

import com.meetpeople.dto.PersonDTO
import com.meetpeople.entity.Person
import com.meetpeople.repository.LocationRepository
import com.meetpeople.repository.MeetingRepository
import com.meetpeople.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val meetingRepository: MeetingRepository,
    private val locationRepository: LocationRepository
): EntityService<Person, PersonDTO> {

    override fun fetchAll(): List<Person> = personRepository.findAll()

    override fun fetchById(id: Long): Optional<Person> = personRepository.findById(id)

    override fun create(dto: PersonDTO): Person = fromDTO(
        (fetchAll().size + 1).toLong(), dto
    )

    override fun update(id: Long, dto: PersonDTO): Person = fromDTO(id, dto)

    override fun delete(id: Long) {
        personRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: PersonDTO): Person = personRepository.save(Person(
        id = id,
        firstname = dto.firstname,
        lastname = dto.lastname,
        password = dto.password,
        phone = dto.phone,
        gender = dto.gender,
        birthday = dto.birthday,
        location = locationRepository.findById(dto.locationId).get(),
        maritalStatus = dto.maritalStatus,
        status = dto.status,
        about = dto.about,
        premium = dto.premium,
        possibleMeeting = meetingRepository.findAllById(dto.possibleMeetings).toSet(),
        meetings = meetingRepository.findAllById(dto.meetings).toSet()
    ))

}
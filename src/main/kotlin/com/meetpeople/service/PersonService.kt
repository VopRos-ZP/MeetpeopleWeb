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
) {

    fun getAll(): List<Person> = personRepository.findAll()

    fun getById(id: Long): Optional<Person> = personRepository.findById(id)

    fun save(dto: PersonDTO): Person = fromDTO(
        (getAll().size + 1).toLong(), dto
    )

    fun update(id: Long, dto: PersonDTO): Person = fromDTO(id, dto)

    fun delete(id: Long) {
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
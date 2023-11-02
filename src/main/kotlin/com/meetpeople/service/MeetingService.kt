package com.meetpeople.service

import com.meetpeople.entity.Meeting
import com.meetpeople.repository.MeetingRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Qualifier
@Service
class MeetingService(private val meetingRepository: MeetingRepository) : EntityService<Meeting> {

    override fun fetchAll(): List<Meeting> = meetingRepository.findAll()

    override fun fetchById(id: Long): Optional<Meeting> = meetingRepository.findById(id)

    override fun create(e: Meeting): Meeting = meetingRepository.save(e.copy(id = meetingRepository.count() + 1))

    override fun update(e: Meeting): Meeting = meetingRepository.save(e)

    override fun delete(id: Long) { meetingRepository.deleteById(id) }

}
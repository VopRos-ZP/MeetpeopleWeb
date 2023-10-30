package com.meetpeople.service

import com.meetpeople.dto.DialogDTO
import com.meetpeople.entity.Dialog
import com.meetpeople.repository.DialogRepository
import com.meetpeople.repository.MeetingRepository
import com.meetpeople.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DialogService(
    private val dialogRepository: DialogRepository,
    private val personRepository: PersonRepository,
    private val meetingRepository: MeetingRepository
): EntityService<Dialog, DialogDTO> {

    override fun fetchAll(): List<Dialog> = dialogRepository.findAll()

    override fun fetchById(id: Long): Optional<Dialog> =dialogRepository.findById(id)

    override fun create(dto: DialogDTO): Dialog = fromDTO(
        (fetchAll().size + 1).toLong(), dto
    )

    override fun update(id: Long, dto: DialogDTO): Dialog = fromDTO(id, dto)

    override fun delete(id: Long) {
        dialogRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: DialogDTO): Dialog = dialogRepository.save(Dialog(
        id = id,
        text = dto.text,
        author = personRepository.findById(dto.author).get(),
        meeting = meetingRepository.findById(dto.meeting).get(),
        date = dto.date,
        isEdited = dto.isEdited,
        reply = dialogRepository.findById(dto.reply ?: 0L).let {
            if (it.isPresent) it.get() else null
        }
    ))

}
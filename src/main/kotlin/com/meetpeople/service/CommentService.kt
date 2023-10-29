package com.meetpeople.service

import com.meetpeople.dto.CommentDTO
import com.meetpeople.entity.Comment
import com.meetpeople.repository.CommentRepository
import com.meetpeople.repository.MeetingRepository
import com.meetpeople.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    private val personRepository: PersonRepository,
    private val meetingRepository: MeetingRepository,
    private val commentRepository: CommentRepository
) : EntityService<Comment, CommentDTO> {

    override fun fetchAll(): List<Comment> = commentRepository.findAll()

    override fun fetchById(id: Long): Optional<Comment> = commentRepository.findById(id)

    override fun create(dto: CommentDTO): Comment = fromDTO(
        (fetchAll().size + 1).toLong(), dto
    )

    override fun update(id: Long, dto: CommentDTO): Comment = fromDTO(id, dto)

    override fun delete(id: Long) {
        commentRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: CommentDTO): Comment = commentRepository.save(Comment(
        id = id,
        text = dto.text,
        author = personRepository.findById(dto.author).get(),
        meeting = meetingRepository.findById(dto.meeting).get(),
        date = dto.date,
        isEdited = dto.isEdited,
        reply = commentRepository.findById(dto.reply).let {
            if (it.isPresent) it.get() else null
        }
    ))
}
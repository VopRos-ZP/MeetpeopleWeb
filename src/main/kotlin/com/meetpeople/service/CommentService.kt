package com.meetpeople.service

import com.meetpeople.entity.Comment
import com.meetpeople.repository.CommentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(private val commentRepository: CommentRepository) : EntityService<Comment> {

    override fun fetchAll(): List<Comment> = commentRepository.findAll()

    override fun fetchById(id: Long): Optional<Comment> = commentRepository.findById(id)

    override fun create(e: Comment): Comment = commentRepository.save(e.copy(id = commentRepository.count() + 1))

    override fun update(e: Comment): Comment = commentRepository.save(e)

    override fun delete(id: Long) { commentRepository.deleteById(id) }
}
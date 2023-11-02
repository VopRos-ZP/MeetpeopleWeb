package com.meetpeople.service

import com.meetpeople.entity.Dialog
import com.meetpeople.repository.DialogRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DialogService(private val dialogRepository: DialogRepository): EntityService<Dialog> {

    override fun fetchAll(): List<Dialog> = dialogRepository.findAll()

    override fun fetchById(id: Long): Optional<Dialog> =dialogRepository.findById(id)

    override fun create(e: Dialog): Dialog = dialogRepository.save(e.copy(id = dialogRepository.count() + 1))

    override fun update(e: Dialog): Dialog = dialogRepository.save(e)

    override fun delete(id: Long) { dialogRepository.deleteById(id) }

}
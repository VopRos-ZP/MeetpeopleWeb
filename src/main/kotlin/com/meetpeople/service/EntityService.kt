package com.meetpeople.service

import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface EntityService<E> {
    fun fetchAll(): List<E>
    fun fetchById(id: Long): Optional<E>
    fun create(e: E): E
    fun update(e: E): E
    fun delete(id: Long)
}
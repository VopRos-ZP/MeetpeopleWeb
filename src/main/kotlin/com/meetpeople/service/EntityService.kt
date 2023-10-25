package com.meetpeople.service

import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface EntityService<E, D> {
    fun fetchAll(): List<E>
    fun fetchById(id: Long): Optional<E>
    fun create(dto: D): E
    fun update(id: Long, dto: D): E
    fun delete(id: Long)
}
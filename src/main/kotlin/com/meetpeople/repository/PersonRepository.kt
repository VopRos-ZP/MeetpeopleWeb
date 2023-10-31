package com.meetpeople.repository

import com.meetpeople.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PersonRepository : JpaRepository<Person, Long> {
    fun findPersonByPhone(phone: String): Optional<Person>
}
package com.meetpeople.service

import com.meetpeople.entity.Person
import com.meetpeople.repository.PersonRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PersonService(private val personRepository: PersonRepository): EntityService<Person>, UserDetailsService {

    override fun fetchAll(): List<Person> = personRepository.findAll()

    override fun fetchById(id: Long): Optional<Person> = personRepository.findById(id)

    override fun create(e: Person): Person = personRepository.save(e.copy(id = personRepository.count() + 1))

    override fun update(e: Person): Person = personRepository.save(e)

    override fun delete(id: Long) { personRepository.deleteById(id) }

    fun findByPhone(phone: String): Optional<Person> = personRepository.findPersonByPhone(phone)

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = personRepository.findPersonByPhone(username!!).orElseThrow {
            UsernameNotFoundException("Пользователь '$username' не найден")
        }
        println(user.phone)
        return User(user.phone, user.password, listOf(SimpleGrantedAuthority("ROLE_USER")))
    }

}
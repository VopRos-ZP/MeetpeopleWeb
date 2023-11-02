package com.meetpeople.service

import com.meetpeople.entity.OnlineStatus
import com.meetpeople.entity.Person
import com.meetpeople.exceptions.Error
import com.meetpeople.request.JwtRequest
import com.meetpeople.response.JwtEntityResponse
import com.meetpeople.utils.JWTTokenUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val personService: PersonService,
    private val tokenUtils: JWTTokenUtils,
    private val authManager: AuthenticationManager
) {

    fun login(authRequest: JwtRequest): ResponseEntity<*> {
        try {
            authManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.phone, authRequest.password))
        } catch (e: BadCredentialsException) {
            return ResponseEntity(Error(HttpStatus.UNAUTHORIZED.value(), "Неверный телефон или пароль"), HttpStatus.UNAUTHORIZED)
        }
        val userDetails = personService.loadUserByUsername(authRequest.phone)
        val token = tokenUtils.generateToken(userDetails)
        val person = personService.findByPhone(authRequest.phone).get().let { person ->
            personService.update(person.copy(onlineStatus = "${OnlineStatus.ONLINE}"))
        }
        return ResponseEntity.ok(JwtEntityResponse(token, person))
    }

    fun registration(person: Person): ResponseEntity<*> {
        return if (personService.findByPhone(person.phone).isPresent) {
            ResponseEntity(Error(HttpStatus.BAD_REQUEST.value(), "Пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST)
        } else {
            personService.create(person.copy(password = passwordEncoder.encode(person.password)))
            ResponseEntity(Error(HttpStatus.OK.value(), "Пользователь успешно зарегестрирован"), HttpStatus.OK)
        }
    }

    fun logout(id: Long): ResponseEntity<*> {
        val person = personService.fetchById(id)
        return if (person.isEmpty) {
            ResponseEntity(Error(HttpStatus.BAD_REQUEST.value(), "Пользователь не зарегистрирован"), HttpStatus.BAD_REQUEST)
        } else {
            personService.update(person.get().copy(onlineStatus = "${OnlineStatus.OFFLINE}"))
            ResponseEntity.ok(Error(HttpStatus.OK.value(), "Пользователь вышел с аккаунта"))
        }
    }
}
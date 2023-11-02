package com.meetpeople.controller

import com.meetpeople.entity.Person
import com.meetpeople.request.JwtRequest
import com.meetpeople.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v0/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody authRequest: JwtRequest): ResponseEntity<*> = authService.login(authRequest)

    @PostMapping("/registration")
    fun registration(@RequestBody person: Person): ResponseEntity<*> = authService.registration(person)

    @PostMapping("/logout/{id}")
    fun logout(@PathVariable id: Long): ResponseEntity<*> = authService.logout(id)

}
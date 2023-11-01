package com.meetpeople.controller

import com.meetpeople.dto.PersonDTO
import com.meetpeople.request.JwtRequest
import com.meetpeople.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody authRequest: JwtRequest): ResponseEntity<*> = authService.login(authRequest)

    @PostMapping("/registration")
    fun registration(@RequestBody dto: PersonDTO): ResponseEntity<*> = authService.registration(dto)

    @PostMapping("/logout")
    fun logout(@RequestBody dto: PersonDTO): ResponseEntity<*> = authService.logout(dto)

}
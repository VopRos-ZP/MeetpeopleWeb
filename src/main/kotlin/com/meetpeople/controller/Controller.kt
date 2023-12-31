package com.meetpeople.controller

import com.meetpeople.exceptions.Error
import com.meetpeople.response.JwtEntityResponse
import com.meetpeople.service.EntityService
import com.meetpeople.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

open class Controller<E>(private val service: EntityService<E>) {

    @GetMapping("/")
    fun getAll(@RequestHeader("Authorization") token: String): ResponseEntity<JwtEntityResponse<List<E>>> =
        ResponseEntity(JwtEntityResponse(Utils.fetchToken(token), service.fetchAll()), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getById(@RequestHeader("Authorization") token: String, @PathVariable id: Long): ResponseEntity<*> {
        val fetch = service.fetchById(id)
        return if (fetch.isEmpty) {
            ResponseEntity(Error(HttpStatus.NOT_FOUND.value(), "Данные по id '$id' не найдены"), HttpStatus.NOT_FOUND)
        } else ResponseEntity(JwtEntityResponse(Utils.fetchToken(token), fetch.get()), HttpStatus.OK)
    }

    @PostMapping("/")
    fun create(@RequestHeader("Authorization") token: String, @RequestBody e: E): ResponseEntity<JwtEntityResponse<E>> =
        ResponseEntity(JwtEntityResponse(Utils.fetchToken(token), service.create(e)), HttpStatus.CREATED)

    @PutMapping("/")
    fun update(@RequestHeader("Authorization") token: String, @RequestBody e: E): ResponseEntity<JwtEntityResponse<E>> =
        ResponseEntity(JwtEntityResponse(Utils.fetchToken(token), service.update(e)), HttpStatus.OK)

    @DeleteMapping("/{id}")
    fun delete(@RequestHeader("Authorization") token: String, @PathVariable id: Long): ResponseEntity<*> =
        ResponseEntity.ok(JwtEntityResponse(Utils.fetchToken(token), service.delete(id)))

}
package com.meetpeople.controller

import com.meetpeople.service.EntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

open class Controller<E, D>(private val service: EntityService<E, D>) {

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<E>> =
        ResponseEntity(service.fetchAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<E> =
        ResponseEntity.of(service.fetchById(id))

    @PostMapping("/")
    fun create(@RequestBody dto: D): ResponseEntity<E> =
        ResponseEntity(service.create(dto), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: D): ResponseEntity<E> =
        ResponseEntity(service.update(id, dto), HttpStatus.OK)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) { service.delete(id) }

}
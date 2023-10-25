package com.meetpeople.controller

import com.meetpeople.dto.PersonDTO
import com.meetpeople.entity.Person
import com.meetpeople.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v0/persons")
class PersonController(private val personService: PersonService) {

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<Person>> =
        ResponseEntity(personService.getAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Person> =
        ResponseEntity.of(personService.getById(id))

    @PostMapping("/")
    fun create(@RequestBody dto: PersonDTO): ResponseEntity<Person> =
        ResponseEntity(personService.save(dto), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: PersonDTO): ResponseEntity<Person> =
        ResponseEntity(personService.update(id, dto), HttpStatus.OK)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        personService.delete(id)
    }

}
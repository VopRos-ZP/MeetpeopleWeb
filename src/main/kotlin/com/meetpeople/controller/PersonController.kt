package com.meetpeople.controller

import com.meetpeople.dto.PersonDTO
import com.meetpeople.entity.Person
import com.meetpeople.service.EntityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v0/persons")
class PersonController(
    service: EntityService<Person, PersonDTO>
): Controller<Person, PersonDTO>(service)
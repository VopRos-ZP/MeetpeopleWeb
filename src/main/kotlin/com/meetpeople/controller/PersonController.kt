package com.meetpeople.controller

import com.meetpeople.entity.Person
import com.meetpeople.service.PersonService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/persons")
class PersonController(service: PersonService): Controller<Person>(service)
package com.meetpeople

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeetpeopleWebApplication

fun main(args: Array<String>) {
    runApplication<MeetpeopleWebApplication>(*args)
}

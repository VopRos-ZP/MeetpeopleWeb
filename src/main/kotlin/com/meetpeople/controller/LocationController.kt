package com.meetpeople.controller

import com.meetpeople.dto.LocationDTO
import com.meetpeople.entity.Location
import com.meetpeople.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v0/locations")
class LocationController(private val locationService: LocationService) {

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<Location>> =
        ResponseEntity(locationService.getAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Location> =
        ResponseEntity.of(locationService.getById(id))

    @PostMapping("/")
    fun create(@RequestBody dto: LocationDTO): ResponseEntity<Location> =
        ResponseEntity(locationService.save(dto), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: LocationDTO): ResponseEntity<Location> =
        ResponseEntity(locationService.update(id, dto), HttpStatus.OK)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        locationService.delete(id)
    }

}
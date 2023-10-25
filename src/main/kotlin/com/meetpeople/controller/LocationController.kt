package com.meetpeople.controller

import com.meetpeople.dto.LocationDTO
import com.meetpeople.entity.Location
import com.meetpeople.service.LocationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/locations")
class LocationController(service: LocationService): Controller<Location, LocationDTO>(service)
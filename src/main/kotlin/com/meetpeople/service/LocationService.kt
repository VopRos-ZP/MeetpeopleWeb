package com.meetpeople.service

import com.meetpeople.entity.Location
import com.meetpeople.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LocationService(private val locationRepository: LocationRepository): EntityService<Location> {

    override fun fetchAll(): List<Location> = locationRepository.findAll()

    override fun fetchById(id: Long): Optional<Location> = locationRepository.findById(id)

    override fun create(e: Location): Location = locationRepository.save(e.copy(id = locationRepository.count() + 1))

    override fun update(e: Location): Location = locationRepository.save(e)

    override fun delete(id: Long) { locationRepository.deleteById(id) }

}
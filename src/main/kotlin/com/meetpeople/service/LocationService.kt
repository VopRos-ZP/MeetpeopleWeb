package com.meetpeople.service

import com.meetpeople.dto.LocationDTO
import com.meetpeople.entity.Location
import com.meetpeople.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getAll(): List<Location> = locationRepository.findAll()

    fun getById(id: Long): Optional<Location> = locationRepository.findById(id)

    fun save(dto: LocationDTO): Location = fromDTO(
        (getAll().size + 1).toLong(), dto
    )

    fun update(id: Long, dto: LocationDTO): Location = fromDTO(id, dto)

    fun delete(id: Long) {
        locationRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: LocationDTO): Location = locationRepository.save(Location(
        id = id,
        country = dto.country,
        city = dto.city,
        coordinates = dto.coordinates
    ))

}
package com.meetpeople.service

import com.meetpeople.dto.LocationDTO
import com.meetpeople.entity.Location
import com.meetpeople.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LocationService(
    private val locationRepository: LocationRepository
): EntityService<Location, LocationDTO> {

    override fun fetchAll(): List<Location> = locationRepository.findAll()

    override fun fetchById(id: Long): Optional<Location> = locationRepository.findById(id)

    override fun create(dto: LocationDTO): Location = fromDTO(
        (fetchAll().size + 1).toLong(), dto
    )

    override fun update(id: Long, dto: LocationDTO): Location = fromDTO(id, dto)

    override fun delete(id: Long) {
        locationRepository.deleteById(id)
    }

    private fun fromDTO(id: Long, dto: LocationDTO): Location = locationRepository.save(Location(
        id = id,
        country = dto.country,
        city = dto.city,
        coordinates = dto.coordinates
    ))

}
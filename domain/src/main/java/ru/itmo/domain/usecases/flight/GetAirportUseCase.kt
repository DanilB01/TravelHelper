package ru.itmo.domain.usecases.flight

import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.repositories.flight.LocationRepository

class GetAirportUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CityModel, List<AirportModel>> {
        return locationRepository.getAirports()
    }
}
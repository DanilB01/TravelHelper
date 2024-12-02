package ru.itmo.domain.usecases.flightUseCases

import ru.itmo.domain.models.flightModels.AirportModel
import ru.itmo.domain.models.flightModels.CityModel
import ru.itmo.domain.repositories.flightRepositories.LocationRepository

class GetAirportUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CityModel, List<AirportModel>> {
        return locationRepository.getAirports()
    }
}
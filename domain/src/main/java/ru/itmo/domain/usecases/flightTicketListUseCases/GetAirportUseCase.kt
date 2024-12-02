package ru.itmo.domain.usecases.flightTicketListUseCases

import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.repositories.flightTicketListRepositories.LocationRepository

class GetAirportUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CityModel, List<AirportModel>> {
        return locationRepository.getAirports()
    }
}
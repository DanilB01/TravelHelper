package ru.itmo.domain.usecases.flightTicketListUseCases

import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.domain.repositories.flightTicketListRepositories.LocationRepository

class GetCityUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CountryModel, List<CityModel>> {
        return locationRepository.getCities()
    }
}
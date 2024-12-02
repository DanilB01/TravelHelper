package ru.itmo.domain.usecases.flightTicketListUseCases

import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.domain.repositories.flightTicketListRepositories.LocationRepository

class GetCountryUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): List<CountryModel> {
        return locationRepository.getCountries()
    }
}
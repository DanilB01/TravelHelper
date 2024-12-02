package ru.itmo.domain.usecases.flightUseCases

import ru.itmo.domain.models.flightModels.CountryModel
import ru.itmo.domain.repositories.flightRepositories.LocationRepository

class GetCountryUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): List<CountryModel> {
        return locationRepository.getCountries()
    }
}
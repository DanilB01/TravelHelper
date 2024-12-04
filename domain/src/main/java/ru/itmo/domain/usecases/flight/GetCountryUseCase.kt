package ru.itmo.domain.usecases.flight

import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.domain.repositories.flight.LocationRepository

class GetCountryUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): List<CountryModel> {
        return locationRepository.getCountries()
    }
}
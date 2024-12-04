package ru.itmo.domain.usecases.flight

import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.domain.repositories.flight.LocationRepository

class GetCityUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CountryModel, List<CityModel>> {
        return locationRepository.getCities()
    }
}
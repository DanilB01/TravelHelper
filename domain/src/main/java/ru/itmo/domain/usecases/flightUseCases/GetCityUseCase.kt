package ru.itmo.domain.usecases.flightUseCases

import ru.itmo.domain.models.flightModels.CityModel
import ru.itmo.domain.models.flightModels.CountryModel
import ru.itmo.domain.repositories.flightRepositories.LocationRepository

class GetCityUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Map<CountryModel, List<CityModel>> {
        return locationRepository.getCities()
    }
}
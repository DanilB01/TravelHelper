package ru.itmo.domain.usecases.flightTicketListUseCases


import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.domain.repositories.flightTicketListRepositories.LocationRepository

class GetLocationUseCase(private val locationRepository: LocationRepository) {
    suspend fun getCountryData(): List<CountryModel> {
        return locationRepository.getCountries()
    }

    suspend fun getCityData(): Map<String, List<CityModel>> {
        return locationRepository.getCities()
    }

    suspend fun getAirportData(): Map<String, List<AirportModel>> {
        return locationRepository.getAirports()
    }

}
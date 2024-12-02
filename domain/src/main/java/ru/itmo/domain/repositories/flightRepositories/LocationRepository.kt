package ru.itmo.domain.repositories.flightRepositories


import ru.itmo.domain.models.flightModels.AirportModel
import ru.itmo.domain.models.flightModels.CityModel
import ru.itmo.domain.models.flightModels.CountryModel

interface LocationRepository {
    suspend fun getCountries(): List<CountryModel>
    suspend fun getCities(): Map<CountryModel, List<CityModel>>
    suspend fun getAirports(): Map<CityModel, List<AirportModel>>
}

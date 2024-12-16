package ru.itmo.domain.repositories.flight


import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel

interface LocationRepository {
    suspend fun getCountries(): List<CountryModel>
    suspend fun getCities(): Map<CountryModel, List<CityModel>>
    suspend fun getAirports(): Map<CityModel, List<AirportModel>>
}

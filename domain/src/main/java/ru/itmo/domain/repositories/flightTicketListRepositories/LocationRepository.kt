package ru.itmo.domain.repositories.flightTicketListRepositories


import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel

interface LocationRepository {
    suspend fun getCountries(): List<CountryModel>
    suspend fun getCities(): Map<CountryModel, List<CityModel>>
    suspend fun getAirports(): Map<CityModel, List<AirportModel>>
}

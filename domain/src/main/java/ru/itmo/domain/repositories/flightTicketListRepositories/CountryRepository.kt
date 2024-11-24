package ru.itmo.domain.repositories.flightTicketListRepositories


import ru.itmo.domain.models.flightTicketListModels.CountryModel

interface CountryRepository {
    suspend fun getCountry(): List<CountryModel>
}

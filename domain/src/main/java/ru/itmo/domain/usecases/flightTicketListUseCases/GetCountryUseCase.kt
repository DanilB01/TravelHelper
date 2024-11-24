package ru.itmo.domain.usecases.flightTicketListUseCases


import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.domain.repositories.flightTicketListRepositories.CountryRepository

class GetCountryUseCase(private val countryRepository: CountryRepository) {
    suspend fun execute(): List<CountryModel> {
        return countryRepository.getCountry()
    }
}
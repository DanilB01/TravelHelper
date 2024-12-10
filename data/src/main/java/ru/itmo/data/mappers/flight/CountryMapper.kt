package ru.itmo.data.mappers.flight

import ru.itmo.data.remote.flight.CountryApiModel
import ru.itmo.domain.models.flight.CountryModel

class CountryMapper {
    fun mapFromApiModel(apiModel: CountryApiModel): CountryModel {
        return CountryModel(
            countryName = apiModel.country_name
        )
    }
}
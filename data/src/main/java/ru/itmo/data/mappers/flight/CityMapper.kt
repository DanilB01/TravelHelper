package ru.itmo.data.mappers.flight

import ru.itmo.data.remote.flight.CityApiModel
import ru.itmo.domain.models.flight.CityModel

class CityMapper {
    fun mapFromApiModel(apiModel: CityApiModel): CityModel {
        return CityModel(
            cityName = apiModel.city_name
        )
    }
}
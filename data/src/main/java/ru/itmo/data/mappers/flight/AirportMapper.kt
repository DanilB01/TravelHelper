package ru.itmo.data.mappers.flight

import ru.itmo.data.remote.flight.AirportApiModel
import ru.itmo.data.remote.flight.CityApiModel
import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel

class AirportMapper {
    fun mapFromApiModel(apiModel: AirportApiModel): AirportModel {
        return AirportModel(
            airportName = apiModel.airport_name
        )
    }
}
package ru.itmo.data.mappers.main

import ru.itmo.data.remote.main.HistoryApiModel
import ru.itmo.domain.models.main.HistoryModel

class HistoryMapper {
    fun mapFromApiModel(apiModel: HistoryApiModel): HistoryModel {
        return HistoryModel(
            travelTimeFrom = apiModel.travel_time_from,
            travelTimeTo = apiModel.travel_time_to,
            travelPlaceFrom = apiModel.travel_place_from,
            travelPlaceTo = apiModel.travel_place_to,
            travelCost = apiModel.travel_cost
        )
    }
}
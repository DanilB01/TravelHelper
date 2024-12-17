package ru.itmo.data.mappers.main

import ru.itmo.data.remote.main.TimeTableApiModel
import ru.itmo.domain.models.main.TimeTableModel

class TimeTableMapper {
    fun mapFromApiModel(apiModel: TimeTableApiModel): TimeTableModel {
        return TimeTableModel(
            timeData = apiModel.time_data,
            descriptionData = apiModel.description_data,
            typeData = apiModel.type_data
        )
    }
}
package ru.itmo.data.repositories.main



import ru.itmo.data.mappers.main.TimeTableMapper
import ru.itmo.data.remote.main.TimeTableApiModel
import ru.itmo.domain.models.main.TimeTableModel
import ru.itmo.domain.repositories.main.TimetableRepository

class TimeTableRepositoryImpl: TimetableRepository {
    private val mapperTimeTable = TimeTableMapper()


    // TODO: Add service implementation
    private val dataList: List<TimeTableApiModel> = listOf(
        TimeTableApiModel("11.40","Вылет","default"),
        TimeTableApiModel("14.25","Вылет","default"),
        TimeTableApiModel("14.40","ресторан FISHBOWL","action1"),
        TimeTableApiModel("15:50","Заселение","default"),
        TimeTableApiModel("17:00","ATTY Gallery","action2"))

    private val dataList2: List<TimeTableApiModel> = listOf()


    override fun getTimeTableData(timeTableDate: String): List<TimeTableModel> {
        // TODO: add variable dependency
        return dataList.map { apiModelTimeTable -> mapperTimeTable.mapFromApiModel(apiModelTimeTable) }
    }


}
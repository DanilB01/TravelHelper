package ru.itmo.domain.repositories.main


import ru.itmo.domain.models.main.TimeTableModel

interface TimetableRepository {
    fun getTimeTableData(timeTableDate: String): List<TimeTableModel>

}

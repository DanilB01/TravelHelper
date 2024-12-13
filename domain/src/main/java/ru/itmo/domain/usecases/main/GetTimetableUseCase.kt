package ru.itmo.domain.usecases.main

import ru.itmo.domain.models.main.TimeTableModel
import ru.itmo.domain.repositories.main.TimetableRepository

class GetTimetableUseCase(private val timetableRepository: TimetableRepository) {
    fun execute(timeTableDate: String): List<TimeTableModel> {
        return timetableRepository.getTimeTableData(timeTableDate)
    }
}
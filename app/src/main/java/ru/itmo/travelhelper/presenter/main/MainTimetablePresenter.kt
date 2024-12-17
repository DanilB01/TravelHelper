package ru.itmo.travelhelper.presenter.main


import ru.itmo.data.repositories.main.TimeTableRepositoryImpl
import ru.itmo.domain.usecases.main.GetTimetableUseCase
import ru.itmo.travelhelper.view.main.MainTimetableView


class MainTimetablePresenter(private val view: MainTimetableView) {
    private val getTimeTableUseCase = GetTimetableUseCase(TimeTableRepositoryImpl())


    fun loadTimeTableData(timeTableDate: String) {
        val timeTableData = getTimeTableUseCase.execute(
            timeTableDate = timeTableDate
        )

        view.getTimeTable(timeTableData)
    }
}
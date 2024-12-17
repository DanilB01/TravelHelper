package ru.itmo.travelhelper.view.main

import ru.itmo.domain.models.main.TimeTableModel

interface MainTimetableView {
    fun getTimeTable(timeTableData: List<TimeTableModel>)
}
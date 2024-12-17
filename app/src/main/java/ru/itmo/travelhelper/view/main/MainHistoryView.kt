package ru.itmo.travelhelper.view.main

import ru.itmo.domain.models.main.HistoryModel

interface MainHistoryView {
    fun getHistoryData(historyData: List<HistoryModel>)
}
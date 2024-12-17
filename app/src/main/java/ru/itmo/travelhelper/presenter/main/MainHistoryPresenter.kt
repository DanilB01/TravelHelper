package ru.itmo.travelhelper.presenter.main

import ru.itmo.data.repositories.main.HistoryRepositoryImpl
import ru.itmo.domain.usecases.main.GetHistoryUseCase
import ru.itmo.travelhelper.view.main.MainHistoryView


class MainHistoryPresenter(private val view: MainHistoryView) {
    private val getHistoryUseCase = GetHistoryUseCase(HistoryRepositoryImpl())


    fun loadHistoryData() {
        val historyData = getHistoryUseCase.execute()

        view.getHistoryData(historyData)
    }
}
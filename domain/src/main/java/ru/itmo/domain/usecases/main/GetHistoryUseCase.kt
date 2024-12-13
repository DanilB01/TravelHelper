package ru.itmo.domain.usecases.main

import ru.itmo.domain.models.main.HistoryModel
import ru.itmo.domain.repositories.main.HistoryRepository

class GetHistoryUseCase(private val historyRepository: HistoryRepository) {
    fun execute(): List<HistoryModel> {
        return historyRepository.getHistoryData()
    }
}
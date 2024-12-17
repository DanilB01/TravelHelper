package ru.itmo.domain.repositories.main

import ru.itmo.domain.models.main.HistoryModel


interface HistoryRepository {
    fun getHistoryData(): List<HistoryModel>
}

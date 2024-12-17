package ru.itmo.data.repositories.main



import ru.itmo.data.mappers.main.HistoryMapper
import ru.itmo.data.remote.main.HistoryApiModel
import ru.itmo.domain.models.main.HistoryModel
import ru.itmo.domain.repositories.main.HistoryRepository

class HistoryRepositoryImpl: HistoryRepository {
    private val mapperHistory = HistoryMapper()


    // TODO: Add service implementation
    private val dataList: List<HistoryApiModel> = listOf(
        HistoryApiModel("04.12.24","09.12.24","Санкт-Петербург","Сеул","150000"),
        HistoryApiModel("05.12.24","10.12.24","Москва","Сеул","150000"),
        HistoryApiModel("06.12.24","11.12.24","Казань","Сидней","350000"),
        HistoryApiModel("07.12.24","12.12.24","Нью-Йорк","Пекин","150000"),
        HistoryApiModel("08.12.24","13.12.24","Челябинск","Мадрид","250000"),)

    private val dataList2: List<HistoryApiModel> = listOf()


    override fun getHistoryData(): List<HistoryModel> {
        return dataList.map { apiModelHistory -> mapperHistory.mapFromApiModel(apiModelHistory) }
    }


}
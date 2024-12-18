package ru.itmo.data.repositories.totalinfo



import ru.itmo.data.mappers.totalinfo.totalInfoPlaceMapper
import ru.itmo.data.remote.totalinfo.TotalInfoPlaceApiModel
import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel
import ru.itmo.domain.repositories.totalinfo.TotalInfoPlaceRepository

class TotalInfoPlaceRepositoryImpl: TotalInfoPlaceRepository {
    private val mapperTotalInfoPlace = totalInfoPlaceMapper()


    // TODO: Add service implementation
    private val dataList: List<TotalInfoPlaceApiModel> = listOf(
        TotalInfoPlaceApiModel("FISHBOWL","Собор Девы Марии","Средний чек: 2000₽","4.5", "fishbowl"),
        TotalInfoPlaceApiModel("ATTY Gallery","Benalla Tourist park","Открыто с 10.00 до 20.00","4.8", "atty_gallery"),
        TotalInfoPlaceApiModel("Собор Девы Марии","около Улицы","Возраст: 12+","4.9", "cathedral_mary"),
        TotalInfoPlaceApiModel("Pizza Boccone","Около парка Культуры","Средний чек: 2687₽","4.5", "pizza_boccone"),)

    private val dataList2: List<TotalInfoPlaceApiModel> = listOf()


    override fun getTotalInfoPlaceData(): List<TotalInfoPlaceModel> {
        return dataList.map { apiModelInterestingPlace-> mapperTotalInfoPlace.mapFromApiModel(apiModelInterestingPlace) }
    }


}
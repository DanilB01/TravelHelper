package ru.itmo.data.repositories.main



import ru.itmo.data.mappers.main.InterestingPlaceMapper
import ru.itmo.data.remote.main.InterestingPlaceApiModel
import ru.itmo.domain.models.main.InterestingPlaceModel
import ru.itmo.domain.repositories.main.InterestingPlaceRepository

class InterestingPlaceRepositoryImpl: InterestingPlaceRepository {
    private val mapperInterestingPlace = InterestingPlaceMapper()


    // TODO: Add service implementation
    private val dataList: List<InterestingPlaceApiModel> = listOf(
        //тут бы поменять на норм названия))
        InterestingPlaceApiModel("Мак","около Набережной","Круглосуточно","5+", "init_picture_1"),
        InterestingPlaceApiModel("Театр","около Дома","Круглосуточно","4+", "init_picture_2"),
        InterestingPlaceApiModel("Музей","около Улицы","Возраст: 6+","5", "init_picture_3"),
        InterestingPlaceApiModel("Кино","там!","Новые фильмы появились","5++", "init_picture_4"),
        InterestingPlaceApiModel("Университет","ломо","Выживание","5+++", "error_name_pic"))

    private val dataList2: List<InterestingPlaceApiModel> = listOf()


    override fun getInterestingPlaceData(): List<InterestingPlaceModel> {
        return dataList.map { apiModelInterestingPlace-> mapperInterestingPlace.mapFromApiModel(apiModelInterestingPlace) }
    }


}
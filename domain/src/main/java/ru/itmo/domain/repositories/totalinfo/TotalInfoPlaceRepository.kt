package ru.itmo.domain.repositories.totalinfo


import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel

interface TotalInfoPlaceRepository {
    fun getTotalInfoPlaceData(): List<TotalInfoPlaceModel>

}

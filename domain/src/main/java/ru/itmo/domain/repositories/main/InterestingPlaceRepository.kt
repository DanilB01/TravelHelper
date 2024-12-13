package ru.itmo.domain.repositories.main


import ru.itmo.domain.models.main.InterestingPlaceModel

interface InterestingPlaceRepository {
    fun getInterestingPlaceData(): List<InterestingPlaceModel>

}

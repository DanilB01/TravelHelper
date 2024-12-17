package ru.itmo.domain.usecases.main

import ru.itmo.domain.models.main.InterestingPlaceModel
import ru.itmo.domain.repositories.main.InterestingPlaceRepository

class GetInterestingPlaceUseCase(private val interestingPlaceRepository: InterestingPlaceRepository) {
    fun execute(): List<InterestingPlaceModel> {
        return interestingPlaceRepository.getInterestingPlaceData()
    }
}
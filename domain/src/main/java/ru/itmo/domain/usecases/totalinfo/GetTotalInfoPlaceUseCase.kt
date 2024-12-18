package ru.itmo.domain.usecases.totalinfo

import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel
import ru.itmo.domain.repositories.totalinfo.TotalInfoPlaceRepository

class GetTotalInfoPlaceUseCase(private val totalInfoPlaceRepository: TotalInfoPlaceRepository) {
    fun execute(): List<TotalInfoPlaceModel> {
        return totalInfoPlaceRepository.getTotalInfoPlaceData()
    }
}
package ru.itmo.domain.usecases.hotelUseCases

import ru.itmo.domain.models.hotelModels.Hotel
import ru.itmo.domain.repositories.hotelRepositories.HotelRepository

class GetHotelsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Hotel> {
        return hotelRepository.getHotels()
    }
}
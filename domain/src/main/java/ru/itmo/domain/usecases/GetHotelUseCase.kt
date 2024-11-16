package ru.itmo.domain.usecases

import ru.itmo.domain.models.Hotel
import ru.itmo.domain.repositories.HotelRepository

class GetHotelsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Hotel> {
        return hotelRepository.getHotels()
    }
}
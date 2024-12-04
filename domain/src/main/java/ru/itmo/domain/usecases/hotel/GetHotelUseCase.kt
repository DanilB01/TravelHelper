package ru.itmo.domain.usecases.hotel

import ru.itmo.domain.models.hotelModels.Hotel
import ru.itmo.domain.repositories.hotel.HotelRepository

class GetHotelsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Hotel> {
        return hotelRepository.getHotels()
    }
}
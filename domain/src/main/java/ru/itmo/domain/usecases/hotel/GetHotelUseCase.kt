package ru.itmo.domain.usecases.hotel

import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.repositories.hotel.HotelRepository

class GetHotelsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Hotel> {
        return hotelRepository.getHotels()
    }
}
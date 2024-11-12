package ru.itmo.travelhelper.domain.usecases

import ru.itmo.travelhelper.domain.models.Hotel
import ru.itmo.travelhelper.domain.repositories.HotelRepository

class GetHotelsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Hotel> {
        return hotelRepository.getHotels()
    }
}
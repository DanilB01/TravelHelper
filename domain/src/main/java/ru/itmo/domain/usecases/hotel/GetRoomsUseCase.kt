package ru.itmo.domain.usecases.hotel

import ru.itmo.domain.models.hotel.Room
import ru.itmo.domain.repositories.hotel.HotelRepository

class GetRoomsUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): List<Room> {
        return hotelRepository.getRooms()
    }
}
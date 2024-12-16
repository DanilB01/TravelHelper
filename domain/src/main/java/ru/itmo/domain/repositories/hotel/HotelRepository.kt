package ru.itmo.domain.repositories.hotel

import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.models.hotel.Room

interface HotelRepository {
    suspend fun getHotels(): List<Hotel>
    suspend fun getRooms(): List<Room>
}

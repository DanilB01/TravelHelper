package ru.itmo.domain.repositories


import ru.itmo.domain.models.Hotel

interface HotelRepository {
    suspend fun getHotels(): List<Hotel>
}

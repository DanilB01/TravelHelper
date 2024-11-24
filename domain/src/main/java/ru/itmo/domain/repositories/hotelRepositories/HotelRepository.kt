package ru.itmo.domain.repositories.hotelRepositories


import ru.itmo.domain.models.hotelModels.Hotel

interface HotelRepository {
    suspend fun getHotels(): List<Hotel>

}

package ru.itmo.data.repositories

import ru.itmo.data.mappers.hotel.HotelMapper
import ru.itmo.data.remote.hotel.HotelApiModel
import ru.itmo.data.remote.hotel.RoomApiModel
import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.models.hotel.Room
import ru.itmo.domain.repositories.hotel.HotelRepository

class HotelRepositoryImpl : HotelRepository {
    // TODO: Add service implementation
    //private val apiService = HotelApiService
    private val mapper = HotelMapper()


    override suspend fun getHotels(): List<Hotel> {
        // Получаем список объектов HotelApiModel из API
        // TODO: add getting hotels from service
        // val apiModels = apiService.getHotels()
        val apiModels = arrayListOf(
            HotelApiModel( "HotelModel name 1", "11:35", 8200),
            HotelApiModel( "HotelModel name 2", "9:05", 21200),
            HotelApiModel( "HotelModel name 3", "8:23", 33100),
            HotelApiModel( "HotelModel name 4", "17:05", 44200),
        )

        // Используем mapper для преобразования каждого HotelApiModel в Hotel
        return apiModels.map { apiModel -> mapper.mapFromApiModel(apiModel) }
    }

    override suspend fun getRooms(): List<Room> {
        return emptyList()
    }
}
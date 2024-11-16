package ru.itmo.data.repositories

import ru.itmo.data.mappers.HotelMapper
import ru.itmo.data.remote.hotel.HotelApiModel
import ru.itmo.data.remote.hotel.HotelApiService
import ru.itmo.domain.models.Hotel
import ru.itmo.domain.repositories.HotelRepository

class HotelRepositoryImpl: HotelRepository {
    // TODO: Add service implementation
    //private val apiService = HotelApiService
    private val mapper = HotelMapper()

    override suspend fun getHotels(): List<Hotel> {
        // Получаем список объектов HotelApiModel из API
        // TODO: add getting hotels from service
        // val apiModels = apiService.getHotels()
        val apiModels = listOf<HotelApiModel>()

        // Используем mapper для преобразования каждого HotelApiModel в Hotel
        return apiModels.map { apiModel -> mapper.mapFromApiModel(apiModel) }
    }
}
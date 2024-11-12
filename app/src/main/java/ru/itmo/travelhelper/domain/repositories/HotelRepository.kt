package ru.itmo.travelhelper.domain.repositories

import ru.itmo.travelhelper.data.mappers.HotelMapper
import ru.itmo.travelhelper.data.remote.HotelApiService
import ru.itmo.travelhelper.domain.models.Hotel

interface HotelRepository {
    suspend fun getHotels(): List<Hotel>
}

class HotelRepositoryImpl(
    private val apiService: HotelApiService,
    private val mapper: HotelMapper
) : HotelRepository {

    override suspend fun getHotels(): List<Hotel> {
        // Получаем список объектов HotelApiModel из API
        val apiModels = apiService.getHotels()

        // Используем mapper для преобразования каждого HotelApiModel в Hotel
        return apiModels.map { apiModel -> mapper.mapFromApiModel(apiModel) }
    }
}
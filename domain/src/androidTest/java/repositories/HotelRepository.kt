package repositories

import mappers.HotelMapper
import remote.HotelApiService

interface HotelRepository {
    suspend fun getHotels(): List<models.Hotel>
}

class HotelRepositoryImpl(
    private val apiService: remote.HotelApiService,
    private val mapper: mappers.HotelMapper
) : HotelRepository {

    override suspend fun getHotels(): List<models.Hotel> {
        // Получаем список объектов HotelApiModel из API
        val apiModels = apiService.getHotels()

        // Используем mapper для преобразования каждого HotelApiModel в Hotel
        return apiModels.map { apiModel -> mapper.mapFromApiModel(apiModel) }
    }
}
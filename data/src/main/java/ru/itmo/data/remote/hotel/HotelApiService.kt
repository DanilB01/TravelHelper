package ru.itmo.data.remote.hotel

interface HotelApiService {
    // TODO: add Retrofit
    //@GET("hotels")
    suspend fun getHotels(): List<HotelApiModel>
}
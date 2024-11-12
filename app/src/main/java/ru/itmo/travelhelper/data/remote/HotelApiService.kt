package ru.itmo.travelhelper.data.remote

interface HotelApiService {
    @GET("hotels")
    suspend fun getHotels(): List<HotelApiModel>

}
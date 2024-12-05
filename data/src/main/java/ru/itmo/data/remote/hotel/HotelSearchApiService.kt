package ru.itmo.data.remote.hotel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header

interface HotelSearchApiService {
    @GET("v3/shopping/hotel-offers")
    fun getHotelPrice(
        @Header("Authorization") authHeader: String, // Добавляем заголовок
        @Query("hotelIds") hotelIds: String,
        @Query("adults") adults: Int,
        @Query("checkInDate") checkInDate: String,
        @Query("checkOutDate") checkOutDate: String,
        @Query("currency") currency: String
    ): Call<HotelSearchResponse>
}

fun createHotelSearchApiService(): HotelSearchApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://test.api.amadeus.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(HotelSearchApiService::class.java)
}

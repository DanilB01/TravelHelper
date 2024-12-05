package ru.itmo.data.remote.hotel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header

interface HotelApiService {
    @GET("v1/reference-data/locations/hotels/by-city")
    fun getHotelsByCity(
        @Query("cityCode") cityCode: String,
        @Header("Authorization") authHeader: String
    ): Call<HotelResponse>
}

fun createHotelApiService(): HotelApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://test.api.amadeus.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(HotelApiService::class.java)
}

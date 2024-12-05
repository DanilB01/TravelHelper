package ru.itmo.data.remote.flight

import FlightsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightApiService {
    @GET("v1/flights")
    fun getFlights(
        @Query("access_key") accessKey: String,
        @Query("dep_iata") departureAirport: String? = null,
        @Query("arr_iata") arriveAirport: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("flight_status") flightStatus: String? = null
    ): Call<FlightsResponse>
}

fun createFlightApiService(): FlightApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.aviationstack.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(FlightApiService::class.java)
}

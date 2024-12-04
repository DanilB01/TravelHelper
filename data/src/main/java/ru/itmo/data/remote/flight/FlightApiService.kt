package ru.itmo.data.remote.flight
import FlightsResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface FlightApiService {
    @GET("v1/flights")
    fun getFlights(
        @Query("access_key") accessKey: String,
        @Query("dep_iata") departureAirport: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("flight_status") flightStatus: String? = null
    ): Call<FlightsResponse>
}

fun main() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.aviationstack.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(FlightApiService::class.java)

    val departureAirport = "SVO" // Код аэропорта (например, Шереметьево для Москвы)

    val call = api.getFlights(
        accessKey = "506e16e900089e0a99e84403009dae3f",
        departureAirport = departureAirport,
        flightStatus = "scheduled",
        limit = 10
    )

    val response = call.execute()

    if (response.isSuccessful) {
        val flightsResponse = response.body()
        flightsResponse?.data?.forEach { flight ->
            println("Рейс: ${flight.flight.iata} (${flight.flight.number})")
            println("Авиакомпания: ${flight.airline.name}")
            println("Отправление: ${flight.departure.airport} (${flight.departure.iata}) в ${flight.departure.scheduled}")
            println("Прибытие: ${flight.arrival.airport} (${flight.arrival.iata}) в ${flight.arrival.scheduled}")
            println("---")
        }
    } else {
        println("Ошибка: ${response.code()} - ${response.message()}")
    }
}



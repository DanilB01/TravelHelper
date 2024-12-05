package ru.itmo.data.remote.common

import FlightsResponse
import ru.itmo.data.remote.flight.createFlightApiService
import ru.itmo.data.remote.hotel.createHotelApiService
import ru.itmo.data.remote.common.createAuthApiService
import retrofit2.Response
import ru.itmo.data.remote.hotel.HotelResponse

fun main() {
    // Получаем токен авторизации
    val authApiService = createAuthApiService()
    val clientId = "qVDyq7DQNFcmGihG4161yQTSDpGM02mW"
    val clientSecret = "OEMaCfsbTS1GOzyF"

    val callToken = authApiService.getAccessToken(
        grantType = "client_credentials",
        clientId = clientId,
        clientSecret = clientSecret
    )

    val responseToken: Response<AccessTokenResponse> = callToken.execute()

    if (responseToken.isSuccessful) {
        val accessToken = responseToken.body()?.access_token
        println("Access Token: $accessToken")

        // Получаем рейсы для города (например, Санкт-Петербург - код аэропорта "LED")
        val flightApiService = createFlightApiService()

        val departureAirport = "LED" // Код аэропорта (например, Пулково для Питера)
        val arriveAirport = "SVO"

        val callFlights = flightApiService.getFlights(
            accessKey = "8a453b1219250435a7fba41188cdcbd2",
            departureAirport = departureAirport,
            arriveAirport = arriveAirport,
            flightStatus = "scheduled",
            limit = 10
        )

        val responseFlights: Response<FlightsResponse> = callFlights.execute()

        if (responseFlights.isSuccessful) {
            val flightsResponse = responseFlights.body()
            flightsResponse?.data?.forEach { flight ->
                println("Рейс: ${flight.flight.iata} (${flight.flight.number})")
                println("Авиакомпания: ${flight.airline.name}")
                println("Отправление: ${flight.departure.airport} (${flight.departure.iata}) в ${flight.departure.scheduled}")
                println("Прибытие: ${flight.arrival.airport} (${flight.arrival.iata}) в ${flight.arrival.scheduled}")
                println("---")
            }

            // Получаем информацию об отелях для города по кодам аэропортов
            if (accessToken != null) {
                val hotelApiService = createHotelApiService()
                val authHeader = "Bearer $accessToken"
                val cityCode = arriveAirport // Можно извлечь код города из информации о рейсах

                val callHotels = hotelApiService.getHotelsByCity(cityCode, authHeader)
                val responseHotels: Response<HotelResponse> = callHotels.execute()

                if (responseHotels.isSuccessful) {
                    val hotelResponse = responseHotels.body()
                    hotelResponse?.data?.forEach { hotel ->
                        println("Отель: ${hotel.name}")
                        println("Адрес: ${hotel.address}")
                        println("---")
                    }
                } else {
                    println("Ошибка при получении отелей: ${responseHotels.code()} - ${responseHotels.message()}")
                }
            }
        } else {
            println("Ошибка при получении рейсов: ${responseFlights.code()} - ${responseFlights.message()}")
        }
    } else {
        println("Ошибка при получении токена: ${responseToken.code()} - ${responseToken.message()}")
    }
}

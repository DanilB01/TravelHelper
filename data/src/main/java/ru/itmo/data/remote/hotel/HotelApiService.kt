package ru.itmo.data.remote.hotel

import HotelSearchResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header
import ru.itmo.data.remote.common.AccessTokenResponse
import ru.itmo.data.remote.common.AuthApiService
import retrofit2.Response

interface HotelApiService {
    @GET("v1/reference-data/locations/hotels/by-city")
    fun getHotelsByCity(
        @Query("cityCode") cityCode: String,
        @Header("Authorization") authHeader: String
    ): Call<HotelResponse>
}



fun main() {
    // Retrofit для получения токена
    val retrofitAuth = Retrofit.Builder()
        .baseUrl("https://test.api.amadeus.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi = retrofitAuth.create(AuthApiService::class.java)

    // Ваши client_id и client_secret
    val clientId = "qVDyq7DQNFcmGihG4161yQTSDpGM02mW"
    val clientSecret = "OEMaCfsbTS1GOzyF"

    // Получаем токен
    val callToken = authApi.getAccessToken(
        grantType = "client_credentials",
        clientId = clientId,
        clientSecret = clientSecret
    )

    val responseToken: Response<AccessTokenResponse> = callToken.execute()

    if (responseToken.isSuccessful) {
        val accessToken = responseToken.body()?.access_token
        println("Access Token: $accessToken")

        // Если токен получен, делаем запрос для получения отелей
        if (accessToken != null) {
            // Retrofit для получения отелей
            val retrofitHotel = Retrofit.Builder()
                .baseUrl("https://test.api.amadeus.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val hotelApi = retrofitHotel.create(HotelApiService::class.java)

            // Запрос для получения отелей
            val cityCode = "SVO" // код города
            val authHeader = "Bearer $accessToken"

            val callHotels = hotelApi.getHotelsByCity(cityCode, authHeader)
            val responseHotels: Response<HotelResponse> = callHotels.execute()

            if (responseHotels.isSuccessful) {
                val hotelResponse = responseHotels.body()
                hotelResponse?.data?.forEach { hotel ->
                    println("Отель: ${hotel.name}")
                    println("hotelId: ${hotel.hotelId}")
                    // Запрашиваем цену проживания для отеля
                    val hotelSearchApiService = createHotelSearchApiService()
                    val hotelSearchCall = hotelSearchApiService.getHotelPrice(
                        authHeader = "Bearer $accessToken",
                        hotelIds = hotel.hotelId,
                        adults = 1,
                        checkInDate = "2024-12-05",
                        checkOutDate = "2024-12-12",
                        currency = "RUB"
                    )
                    println(hotel.hotelId)

                    val hotelSearchResponse: Response<HotelSearchResponse> =
                        hotelSearchCall.execute()
                    if (hotelSearchResponse.isSuccessful) {
                        val hotelSearch = hotelSearchResponse.body()
                        val offers = hotelSearch?.data?.firstOrNull()?.offers
                        if (offers != null && offers.isNotEmpty()) {
                            val price = offers[0].price.total
                            val currency = offers[0].price.currency
                            println("Цена за проживание: $price $currency")
                        } else {
                            println("Цена не доступна для данного отеля.")
                            println("---")
                        }
                    } else {
                        println("Ошибка при получении отелей: ${responseHotels.code()} - ${responseHotels.message()}")
                    }
                }
            } else {
                println("Ошибка при получении токена: ${responseToken.code()} - ${responseToken.message()}")
            }
        }

    }
}


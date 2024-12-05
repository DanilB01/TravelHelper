package ru.itmo.data.remote.restaurant

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestaurantApiService {
    @GET("v3/places/search")
    fun getRestaurants(
        @Header("Authorization") accessKey: String,  // Используем заголовок Authorization
        @Query("categories") categories: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("near") near: String? = null
    ): Call<RestaurantResponse>
}

fun createRestaurantApiService(): RestaurantApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.foursquare.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(RestaurantApiService::class.java)
}

fun main() {
    val restaurantApiService = createRestaurantApiService()
    val callRestaurants = restaurantApiService.getRestaurants(
        accessKey = "fsq3clhcAmnkvsIgBjq70YKC/FFbBEs0strnaCUL7tCoF4s=",  // Передаем ключ в заголовке
        categories = "13065",  // Пример: категория "Рестораны"
        limit = 10,
        near = "SVO"
    )

    val responseRestaurants = callRestaurants.execute()

    if (responseRestaurants.isSuccessful) {
        val restaurantResponse = responseRestaurants.body()
        restaurantResponse?.results?.forEach { restaurant ->
            println("Ресторан: ${restaurant.name}")
            println("Адрес: ${restaurant.location.formatted_address}")
            println("---")
        }
    } else {
        println("Ошибка при поиске ресторанов: ${responseRestaurants.code()} - ${responseRestaurants.message()}")
    }
}

package ru.itmo.data.remote.restaurant

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApiService {
    @GET("v3/places/search")
    fun getRestaurants(
        @Header("Authorization") accessKey: String,  // Используем заголовок Authorization
        @Query("categories") categories: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("near") near: String? = null
    ): Call<RestaurantResponse>

    // Новый метод для получения фото по fsq_id с дополнительным параметром classifications
    @GET("v3/places/{fsq_id}/photos")
    fun getRestaurantPhotos(
        @Header("Authorization") accessKey: String,
        @Path("fsq_id") fsqId: String, // Используем {fsq_id} для подставления идентификатора ресторана
        @Query("limit") limit: Int = 1,  // Ограничиваем количество фото
        @Query("classifications") classifications: List<String> = listOf("outdoor") // Фильтруем фото по классификации
    ): Call<List<PhotoResponse>>
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

    // Получаем список ресторанов
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
            println(restaurant.fsq_id) // По нему ищем фото
            println("---")

            // Теперь получаем фото для текущего ресторана с классификацией "outdoor"
            val callPhotos = restaurantApiService.getRestaurantPhotos(
                accessKey = "fsq3clhcAmnkvsIgBjq70YKC/FFbBEs0strnaCUL7tCoF4s=",
                fsqId = restaurant.fsq_id,
                classifications = listOf("outdoor")  // Передаем список классификаций
            )

            val responsePhotos = callPhotos.execute()

            if (responsePhotos.isSuccessful) {
                val photoResponse = responsePhotos.body()
                photoResponse?.let {
                    if (it.isNotEmpty()) {
                        // Получаем ссылку на фото
                        val photo = it[0]
                        val photoUrl = "${photo.prefix}original${photo.suffix}"
                        println("Фото: $photoUrl")
                    } else {
                        println("Фото не найдено")
                    }
                }
            } else {
                println("Ошибка при поиске фото: ${responsePhotos.code()} - ${responsePhotos.message()}")
            }
        }
    } else {
        println("Ошибка при поиске ресторанов: ${responseRestaurants.code()} - ${responseRestaurants.message()}")
    }
}




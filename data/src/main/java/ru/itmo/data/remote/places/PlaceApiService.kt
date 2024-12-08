package ru.itmo.data.remote.places

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itmo.data.remote.restaurant.RestaurantResponse

interface PlaceApiService {
    @GET("v3/places/search")
    fun getPlaces(
        @Header("Authorization") accessKey: String,  // Используем заголовок Authorization
        @Query("categories") categories: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("near") near: String? = null
    ): Call<PlaceResponse>

    // Новый метод для получения фото по fsq_id с дополнительным параметром classifications
    @GET("v3/places/{fsq_id}/photos")
    fun getPlacePhotos(
        @Header("Authorization") accessKey: String,
        @Path("fsq_id") fsqId: String, // Используем {fsq_id} для подставления идентификатора ресторана
        @Query("limit") limit: Int = 1,  // Ограничиваем количество фото
        @Query("classifications") classifications: List<String> = listOf("outdoor") // Фильтруем фото по классификации
    ): Call<List<PhotoResponse>>
}

fun createPlaceApiService(): PlaceApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.foursquare.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(PlaceApiService::class.java)
}

fun main() {
    val placeApiService = createPlaceApiService()

    // Получаем список ресторанов
    val callPlaces = placeApiService.getPlaces(
        accessKey = "fsq3clhcAmnkvsIgBjq70YKC/FFbBEs0strnaCUL7tCoF4s=",  // Передаем ключ в заголовке
        categories = "10000",  // категория "Искусство и развлечения"
        limit = 10,
        near = "Москва"
    )

    val responsePlaces = callPlaces.execute()

    if (responsePlaces.isSuccessful) {
        val placeResponse = responsePlaces.body()
        placeResponse?.results?.forEach { place ->
            println("Место: ${place.name}")
            println("Адрес: ${place.location.formatted_address}")
            println(place.fsq_id) // По нему ищем фото
            println("---")

            // Теперь получаем фото для текущего ресторана с классификацией "outdoor"
            val callPhotos = placeApiService.getPlacePhotos(
                accessKey = "fsq3clhcAmnkvsIgBjq70YKC/FFbBEs0strnaCUL7tCoF4s=",
                fsqId = place.fsq_id,
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
        println("Ошибка при поиске ресторанов: ${responsePlaces.code()} - ${responsePlaces.message()}")
    }
}




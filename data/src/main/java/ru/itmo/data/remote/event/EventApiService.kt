package ru.itmo.data.remote.event

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface EventApiService {
    @GET("discovery/v2/events.json")
    fun getEvents(
        @Query("size") size: Int,
        @Query("city") city: String,
        @Query("apikey") apiKey: String
    ): Call<EventResponse>
}

fun main() {


    val retrofit = Retrofit.Builder()
        .baseUrl("https://app.ticketmaster.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(EventApiService::class.java)

    val call = api.getEvents(size = 10, city = "London", apiKey = "bAabFoUQhStvIAonhfwwLvQOXWE7FXIS")

    call.enqueue(object : retrofit2.Callback<EventResponse> {
        override fun onResponse(call: Call<EventResponse>, response: retrofit2.Response<EventResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                apiResponse?.let {
                    val events = it._embedded.events
                    val seenEvents = mutableSetOf<String>() // Для отслеживания уникальных событий
                    println("Events in London:")

                    // Вывод нескольких событий
                    events.forEach { event ->
                        if (seenEvents.add(event.name)) {
                            println("- ${event.name}")
                            val imageUrl = event.images.firstOrNull()?.url ?: "No image available"
                            println("  Image: $imageUrl")
                        }
                    }
                }
            } else {
                println("Request failed: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<EventResponse>, t: Throwable) {
            println("Error: ${t.message}")
        }
    })
}
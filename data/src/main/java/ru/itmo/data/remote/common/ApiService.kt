//import retrofit2.Response
//import ru.itmo.data.remote.common.AccessTokenResponse
//import ru.itmo.data.remote.common.createAuthApiService
//import ru.itmo.data.remote.flight.createFlightApiService
//import ru.itmo.data.remote.hotel.HotelResponse
//import ru.itmo.data.remote.hotel.createHotelApiService
//import ru.itmo.data.remote.hotel.createHotelSearchApiService
//import ru.itmo.data.remote.restaurant.createRestaurantApiService
//
//fun main() {
//    // Получаем токен авторизации
//    val authApiService = createAuthApiService()
//    val clientId = "qVDyq7DQNFcmGihG4161yQTSDpGM02mW"
//    val clientSecret = "OEMaCfsbTS1GOzyF"
//
//    val callToken = authApiService.getAccessToken(
//        grantType = "client_credentials",
//        clientId = clientId,
//        clientSecret = clientSecret
//    )
//
//    val responseToken: Response<AccessTokenResponse> = callToken.execute()
//
//    if (responseToken.isSuccessful) {
//        val accessToken = responseToken.body()?.access_token
//        println("Access Token: $accessToken")
//
//        // Получаем рейсы для города (например, Санкт-Петербург - код аэропорта "LED")
//        val flightApiService = createFlightApiService()
//
//        val departureAirport = "LED" // Код аэропорта (например, Пулково для Питера)
//        val arriveAirport = "SVO"
//
//        val callFlights = flightApiService.getFlights(
//            accessKey = "8a453b1219250435a7fba41188cdcbd2",
//            departureAirport = departureAirport,
//            arriveAirport = arriveAirport,
//            flightStatus = "scheduled",
//            limit = 10
//        )
//
//        val responseFlights: Response<FlightsResponse> = callFlights.execute()
//
//        if (responseFlights.isSuccessful) {
//            val flightsResponse = responseFlights.body()
//            flightsResponse?.data?.forEach { flight ->
//                println("Рейс: ${flight.flight.iata} (${flight.flight.number})")
//                println("Авиакомпания: ${flight.airline.name}")
//                println("Отправление: ${flight.departure.airport} (${flight.departure.iata}) в ${flight.departure.scheduled}")
//                println("Прибытие: ${flight.arrival.airport} (${flight.arrival.iata}) в ${flight.arrival.scheduled}")
//                println("---")
//            }
//
//            // Получаем информацию об отелях
//            if (accessToken != null) {
//                val hotelApiService = createHotelApiService()
//                val authHeader = "Bearer $accessToken"
//                val cityCode = arriveAirport
//
//                val callHotels = hotelApiService.getHotelsByCity(cityCode, authHeader)
//                val responseHotels: Response<HotelResponse> = callHotels.execute()
//
//                if (responseHotels.isSuccessful) {
//                    val hotelResponse = responseHotels.body()
//                    hotelResponse?.data?.forEach { hotel ->
//                        println("Отель: ${hotel.name}")
//                        println("hotelId: ${hotel.hotelId}")
//
//                        // Запрашиваем цену проживания для отеля
//                        val hotelSearchApiService = createHotelSearchApiService()
//                        val hotelSearchCall = hotelSearchApiService.getHotelPrice(
//                            authHeader = "Bearer $accessToken",
//                            hotelIds = hotel.hotelId,
//                            adults = 1,
//                            checkInDate = "2024-12-05",
//                            checkOutDate = "2024-12-12",
//                            currency = "RUB"
//                        )
//
//                        val hotelSearchResponse: Response<HotelSearchResponse> = hotelSearchCall.execute()
//                        if (hotelSearchResponse.isSuccessful) {
//                            val hotelSearch = hotelSearchResponse.body()
//                            val offers = hotelSearch?.data?.firstOrNull()?.offers
//                            if (offers != null && offers.isNotEmpty()) {
//                                val price = offers[0].price.total
//                                val currency = offers[0].price.currency
//                                println("Цена за проживание: $price $currency")
//                            } else {
//                                println("Цена не доступна для данного отеля.")
//                            }
//                        } else {
//                            println("Ошибка при получении цены: ${hotelSearchResponse.code()} - ${hotelSearchResponse.message()}")
//                        }
//                        println("---")
//                    }
//
//                    // Поиск ресторанов
//                    val restaurantApiService = createRestaurantApiService()
//                    val callRestaurants = restaurantApiService.getRestaurants(
//                        accessKey = "8a453b1219250435a7fba41188cdcbd2",
//                        categories = "13065", // Пример: категория "Рестораны"
//                        limit = 10,
//                        near = "SVO"
//                    )
//
//                    val responseRestaurants = callRestaurants.execute()
//
//                    if (responseRestaurants.isSuccessful) {
//                        val restaurantResponse = responseRestaurants.body()
//                        restaurantResponse?.results?.forEach { restaurant ->
//                            println("Ресторан: ${restaurant.name}")
//                            println("Адрес: ${restaurant.location.formatted_address}")
//                            println("Рейтинг: ${restaurant.rating}")
//                            println("Цена: ${restaurant.price}")
//                            println("---")
//                        }
//                    } else {
//                        println("Ошибка при поиске ресторанов: ${responseRestaurants.code()} - ${responseRestaurants.message()}")
//                    }
//                } else {
//                    println("Ошибка при получении отелей: ${responseHotels.code()} - ${responseHotels.message()}")
//                }
//            }
//        } else {
//            println("Ошибка при получении рейсов: ${responseFlights.code()} - ${responseFlights.message()}")
//        }
//    } else {
//        println("Ошибка при получении токена: ${responseToken.code()} - ${responseToken.message()}")
//    }
//}

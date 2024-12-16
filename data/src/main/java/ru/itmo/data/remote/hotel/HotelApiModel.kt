package ru.itmo.data.remote.hotel

data class HotelApiModel(
    val hotelName: String,
    val checkInTime: String,
    val minPrice: Int,
    val description: String = "",
    val websiteURL: String = "https://www.google.ru/"
)

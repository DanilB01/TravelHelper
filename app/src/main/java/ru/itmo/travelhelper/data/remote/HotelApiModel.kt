package ru.itmo.travelhelper.data.remote

data class HotelApiModel(
    val hotel_name: String,
    val hotel_stars: Int,
    val hotel_address: String,
    val available_rooms: Int
)

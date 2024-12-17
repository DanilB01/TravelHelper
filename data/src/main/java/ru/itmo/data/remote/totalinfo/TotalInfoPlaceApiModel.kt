package ru.itmo.data.remote.totalinfo

data class TotalInfoPlaceApiModel(
    val place_name: String,
    val place_location: String,
    val place_description: String,
    val place_rating: String,
    val picture_name: String,
)

package ru.itmo.data.remote.main

data class HistoryApiModel(
    val travel_time_from: String,
    val travel_time_to: String,
    val travel_place_from: String,
    val travel_place_to: String,
    val travel_cost: String
)

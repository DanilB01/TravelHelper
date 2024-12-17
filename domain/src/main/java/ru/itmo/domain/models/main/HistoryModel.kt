package ru.itmo.domain.models.main

data class HistoryModel(
    val travelTimeFrom: String,
    val travelTimeTo: String,
    val travelPlaceFrom: String,
    val travelPlaceTo: String,
    val travelCost: String
)

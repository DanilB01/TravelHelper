package ru.itmo.domain.models.activities

data class Event(
    val title: String,
    val schedule: String,
    val rating: Double,
    val photoResId: Int // ID ресурса для изображения
)


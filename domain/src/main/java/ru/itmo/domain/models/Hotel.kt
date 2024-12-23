package ru.itmo.domain.models

data class Hotel(
    val name: String,
    val stars: Int,
    val address: String,
    val roomsAvailable: Int
)
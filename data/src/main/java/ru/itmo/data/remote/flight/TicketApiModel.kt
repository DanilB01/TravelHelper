package ru.itmo.data.remote.flight

data class TicketApiModel(
    val company_name: String,
    val ticket_cost: String,
    val flight_time_from: String,
    val flight_time_to: String,
)

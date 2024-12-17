package ru.itmo.domain.models.flight

data class TicketModel(
    val companyName: String,
    val ticketCost: String,
    val flightTimeFrom: String,
    val flightTimeTo: String,
)

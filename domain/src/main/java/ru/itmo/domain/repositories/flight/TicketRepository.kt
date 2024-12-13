package ru.itmo.domain.repositories.flight


import ru.itmo.domain.models.flight.TicketModel

interface TicketRepository {
    fun getTicketData(placeFrom: String, placeTo: String, ticketDate: String): List<TicketModel>

}

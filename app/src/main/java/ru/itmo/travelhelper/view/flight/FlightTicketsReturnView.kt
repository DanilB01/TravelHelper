package ru.itmo.travelhelper.view.flight

import ru.itmo.domain.models.flight.TicketModel

interface FlightTicketsReturnView {
    fun getTickets(ticketsData: List<TicketModel>)
}

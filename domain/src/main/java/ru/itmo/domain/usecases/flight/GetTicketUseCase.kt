package ru.itmo.domain.usecases.flight

import ru.itmo.domain.models.flight.TicketModel
import ru.itmo.domain.repositories.flight.TicketRepository

class GetTicketUseCase(private val ticketRepository: TicketRepository) {
    fun execute(placeFrom: String, placeTo: String, ticketDate: String): List<TicketModel> {
        return ticketRepository.getTicketData(placeFrom, placeTo, ticketDate)
    }
}
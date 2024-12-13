package ru.itmo.data.mappers.flight

import ru.itmo.data.remote.flight.TicketApiModel
import ru.itmo.domain.models.flight.TicketModel

class TicketMapper {
    fun mapFromApiModel(apiModel: TicketApiModel): TicketModel {
        return TicketModel(
            companyName = apiModel.company_name,
            ticketCost = apiModel.ticket_cost,
            flightTimeFrom = apiModel.flight_time_from,
            flightTimeTo = apiModel.flight_time_to,
        )
    }
}
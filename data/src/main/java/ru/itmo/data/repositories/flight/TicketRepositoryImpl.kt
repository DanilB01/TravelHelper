package ru.itmo.data.repositories.flight



import ru.itmo.data.mappers.flight.TicketMapper
import ru.itmo.data.remote.flight.TicketApiModel
import ru.itmo.domain.models.flight.TicketModel
import ru.itmo.domain.repositories.flight.TicketRepository

class TicketRepositoryImpl: TicketRepository {
    private val mapperTicket = TicketMapper()


    // TODO: Add service implementation
    private val dataList: List<TicketApiModel> = listOf(
        TicketApiModel("Россия", "27000", "00.12", "09.47",),
        TicketApiModel("S7 Airlines", "19000", "01.45", "11.23",),
        TicketApiModel("Казах Эйр", "25000", "03.34", "15.09",),
        TicketApiModel("Смартавиа", "1234", "02.11", "16.34",),
        TicketApiModel("Emirates", "78000", "17.44", "21.00",))

    override fun getTicketData(placeFrom: String, placeTo: String, ticketDate: String): List<TicketModel> {
        // TODO: add variable dependency
        return dataList.map { apiModelTicket -> mapperTicket.mapFromApiModel(apiModelTicket) }
    }


}
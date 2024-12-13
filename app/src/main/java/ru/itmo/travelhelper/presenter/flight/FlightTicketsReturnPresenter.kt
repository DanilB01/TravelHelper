package ru.itmo.travelhelper.presenter.flight



import ru.itmo.travelhelper.view.flight.FlightTicketsReturnView
import ru.itmo.data.repositories.flight.TicketRepositoryImpl
import ru.itmo.domain.usecases.flight.GetTicketUseCase

class FlightTicketsReturnPresenter(private val view: FlightTicketsReturnView) {
    private var savedTicketReturnProgress: Int = 0
    private val getTicketUseCase = GetTicketUseCase(TicketRepositoryImpl())



    fun loadReturnTicketData(placeFrom: String, placeTo: String, ticketDate: String) {
        val ticketsData = getTicketUseCase.execute(
            placeFrom = placeFrom,
            placeTo = placeTo,
            ticketDate = ticketDate
        )

        view.getTickets(ticketsData)
    }


    fun updateSavedTicketReturnProgress(dataToSave: Int) {
        this.savedTicketReturnProgress = dataToSave
    }


    fun giveTicketReturnProgress(): Int {
        return savedTicketReturnProgress
    }

}
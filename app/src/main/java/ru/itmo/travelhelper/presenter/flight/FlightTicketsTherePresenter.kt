package ru.itmo.travelhelper.presenter.flight



import ru.itmo.data.repositories.flight.TicketRepositoryImpl
import ru.itmo.domain.usecases.flight.GetTicketUseCase
import ru.itmo.travelhelper.view.flight.FlightTicketsThereView

class FlightTicketsTherePresenter(private val view: FlightTicketsThereView) {
    private var savedTicketThereProgress: Int = 0
    private val getTicketUseCase = GetTicketUseCase(TicketRepositoryImpl())



    fun loadThereTicketData(placeFrom: String, placeTo: String, ticketDate: String) {
        val ticketsData = getTicketUseCase.execute(
            placeFrom = placeFrom,
            placeTo = placeTo,
            ticketDate = ticketDate
        )

        view.getTickets(ticketsData)
    }


    fun updateSavedTicketThereProgress(dataToSave: Int) {
        this.savedTicketThereProgress = dataToSave
    }


    fun giveTicketThereProgress(): Int {
        return savedTicketThereProgress
    }

}
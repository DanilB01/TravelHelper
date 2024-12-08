package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.view.flight.FlightTicketsReturnView

class FlightTicketsReturnPresenter(private val view: FlightTicketsReturnView) {
    private var savedTicketReturnProgress: Int = 0


    fun updateSavedTicketReturnProgress(dataToSave: Int) {
        this.savedTicketReturnProgress = dataToSave
    }


    fun giveTicketReturnProgress(): Int {
        return savedTicketReturnProgress
    }

}
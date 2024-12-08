package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.screens.flight.FlightTicketsReturnFragment

class FlightTicketsReturnPresenter(private val view: FlightTicketsReturnFragment) {
    private var savedTicketReturnProgress: Int = 0


    fun updateSavedTicketReturnProgress(dataToSave: Int) {
        this.savedTicketReturnProgress = dataToSave
    }


    fun giveTicketReturnProgress(): Int {
        return savedTicketReturnProgress
    }

}
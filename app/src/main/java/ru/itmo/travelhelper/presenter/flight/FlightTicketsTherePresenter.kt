package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.screens.flight.FlightTicketsThereFragment

class FlightTicketsTherePresenter(private val view: FlightTicketsThereFragment) {
    private var savedTicketThereProgress: Int = 0


    fun updateSavedTicketThereProgress(dataToSave: Int) {
        this.savedTicketThereProgress = dataToSave
    }


    fun giveTicketThereProgress(): Int {
        return savedTicketThereProgress
    }

}
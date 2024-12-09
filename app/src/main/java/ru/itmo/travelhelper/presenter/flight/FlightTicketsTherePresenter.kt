package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.view.flight.FlightTicketsThereView

class FlightTicketsTherePresenter(private val view: FlightTicketsThereView) {
    private var savedTicketThereProgress: Int = 0


    fun updateSavedTicketThereProgress(dataToSave: Int) {
        this.savedTicketThereProgress = dataToSave
    }


    fun giveTicketThereProgress(): Int {
        return savedTicketThereProgress
    }

}
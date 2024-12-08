package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.view.flight.FlightDateView

class FlightDatePresenter(private val view: FlightDateView) {
    private var savedDateData = mutableListOf("","","")


    fun updateFullSavedDateData(dataToSave: MutableList<String>) {
        this.savedDateData = dataToSave
    }

    fun updateExactIndexSavedDateData(dataIndexToSave: String, index: Int) {
        this.savedDateData[index] = dataIndexToSave
    }

    fun giveDateData(): MutableList<String> {
        return savedDateData
    }

}
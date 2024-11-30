package ru.itmo.travelhelper.presenter.flightPresentors



import ru.itmo.travelhelper.screens.flightScreens.FlightDateFragment

class FlightPresenterDateFragment(private val view: FlightDateFragment) {
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
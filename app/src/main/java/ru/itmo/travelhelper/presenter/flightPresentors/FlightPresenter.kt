package ru.itmo.travelhelper.presenter.flightPresentors


import ru.itmo.travelhelper.view.flightViews.FlightActivityView

class FlightPresenter(private val view: FlightActivityView) {
    var globalSavedArrivalData = mutableListOf("","","")
    var globalSavedDepartureData = mutableListOf("","","")
    var globalSavedDateData = mutableListOf("","","")
    var isDepartureFull: Boolean = false
    var isArrivalFull: Boolean = false

    fun giveArrivalData(): MutableList<String> {
        return globalSavedArrivalData
    }

    fun giveDepartureData(): MutableList<String> {
        return globalSavedDepartureData
    }

    fun giveDateData(): MutableList<String> {
        return globalSavedDateData
    }


    fun updateGlobalSavedArrivalData(dataToSave: MutableList<String>) {
        this.globalSavedArrivalData = dataToSave
    }

    fun updateGlobalSavedDepartureData(dataToSave: MutableList<String>) {
        this.globalSavedDepartureData = dataToSave
    }

    fun updateGlobalSavedDateData(dataToSave: MutableList<String>) {
        this.globalSavedDateData = dataToSave
    }

    fun updateIsDepartureFull(dataToSave: Boolean) {
        this.isDepartureFull = dataToSave
    }

    fun updateIsArrivalFull(dataToSave: Boolean) {
        this.isArrivalFull = dataToSave
    }

}
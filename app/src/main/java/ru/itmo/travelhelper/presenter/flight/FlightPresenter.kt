package ru.itmo.travelhelper.presenter.flight


import ru.itmo.travelhelper.view.flight.FlightView

class FlightPresenter(private val view: FlightView) {
    var globalSavedArrivalData = mutableListOf("","","")
    var globalSavedDepartureData = mutableListOf("","","")
    var globalSavedDateData = mutableListOf("","","")
    var globalSavedTicketThereData = 0
    var globalSavedTicketReturnData = 0
    var isDepartureFull: Boolean = false
    var isArrivalFull: Boolean = false
    var isDateFull: Boolean = false

    fun giveArrivalData(): MutableList<String> {
        return globalSavedArrivalData
    }

    fun giveDepartureData(): MutableList<String> {
        return globalSavedDepartureData
    }

    fun giveDateData(): MutableList<String> {
        return globalSavedDateData
    }

    fun giveTicketThereData(): Int {
        return globalSavedTicketThereData
    }

    fun giveTicketReturnData(): Int {
        return globalSavedTicketReturnData
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

    fun updateGlobalSavedTicketThereData(dataToSave: Int) {
        this.globalSavedTicketThereData = dataToSave
    }

    fun updateGlobalSavedTicketReturnData(dataToSave: Int) {
        this.globalSavedTicketReturnData = dataToSave
    }


    fun updateIsDepartureFull(dataToSave: Boolean) {
        this.isDepartureFull = dataToSave
    }

    fun updateIsArrivalFull(dataToSave: Boolean) {
        this.isArrivalFull = dataToSave
    }

    fun updateIsDateFull(dataToSave: Boolean) {
        this.isDateFull = dataToSave
    }

    fun getIsReturnBoxChecked(): Boolean {
        return (globalSavedDateData[2] == "ReturnChecked_True")
    }



}
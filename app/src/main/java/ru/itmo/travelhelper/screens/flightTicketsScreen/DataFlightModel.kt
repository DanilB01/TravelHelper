package ru.itmo.travelhelper.screens.flightTicketsScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataFlightModel: ViewModel() {
    val locationArrivalDataMessage: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }

    val locationDepartureDataMessage: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }
}
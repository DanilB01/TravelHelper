package ru.itmo.travelhelper.screens.flightTicketsScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataFlightModel: ViewModel() {
    val locationDataMessage: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }
}
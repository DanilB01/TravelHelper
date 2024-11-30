package ru.itmo.travelhelper.presenter.flightPresentors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flightTicketListRepositoriesImpl.LocationRepositoryImpl
import ru.itmo.domain.usecases.flightTicketListUseCases.GetLocationUseCase
import ru.itmo.travelhelper.screens.flightScreens.FlightArrivalFragment

class FlightPresenterArrivalFragment(private val view: FlightArrivalFragment) {
    private val getLocationUseCase = GetLocationUseCase(LocationRepositoryImpl())
    private var savedArrivalData = mutableListOf("","","")


    fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadLocationData()
        }
    }


    private suspend fun loadLocationData() {
        val countriesData = getLocationUseCase.getCountryData()
        val citiesData = getLocationUseCase.getCityData()
        val airportsData = getLocationUseCase.getAirportData()

        view.getCountries(countriesData)
        view.getCitiesMap(citiesData)
        view.getAirportMap(airportsData)
    }


    fun giveArrivalData(): MutableList<String> {
        return savedArrivalData
    }

    fun updateFullSavedArrivalData(dataToSave: MutableList<String>) {
        this.savedArrivalData = dataToSave
    }

    fun updateExactIndexSavedArrivalData(dataIndexToSave: String, index: Int) {
        this.savedArrivalData[index] = dataIndexToSave
    }






}
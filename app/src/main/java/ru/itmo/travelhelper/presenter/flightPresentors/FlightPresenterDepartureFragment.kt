package ru.itmo.travelhelper.presenter.flightPresentors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flightTicketListRepositoriesImpl.LocationRepositoryImpl
import ru.itmo.domain.usecases.flightTicketListUseCases.GetLocationUseCase
import ru.itmo.travelhelper.screens.flightScreens.FlightDepartureFragment

class FlightPresenterDepartureFragment(private val view: FlightDepartureFragment) {
    private val getLocationUseCase = GetLocationUseCase(LocationRepositoryImpl())
    private var savedDepartureData = mutableListOf("","","")

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


    fun updateFullSavedDepartureData(dataToSave: MutableList<String>) {
        this.savedDepartureData = dataToSave
    }

    fun updateExactIndexSavedDepartureData(dataIndexToSave: String, index: Int) {
        this.savedDepartureData[index] = dataIndexToSave
    }

    fun giveDepartureData(): MutableList<String> {
        return savedDepartureData
    }







}
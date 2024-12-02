package ru.itmo.travelhelper.presenter.flightPresentors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flightTicketListRepositoriesImpl.LocationRepositoryImpl
import ru.itmo.domain.usecases.flightTicketListUseCases.GetAirportUseCase
import ru.itmo.domain.usecases.flightTicketListUseCases.GetCityUseCase
import ru.itmo.domain.usecases.flightTicketListUseCases.GetCountryUseCase
import ru.itmo.travelhelper.screens.flightScreens.FlightDepartureFragment

class FlightPresenterDeparture(private val view: FlightDepartureFragment) {
    private val getCountryUseCase = GetCountryUseCase(LocationRepositoryImpl())
    private val getCityUseCase = GetCityUseCase(LocationRepositoryImpl())
    private val getAirportUseCase = GetAirportUseCase(LocationRepositoryImpl())
    private var savedDepartureData = mutableListOf("","","")

    fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadLocationData()
        }
    }


    private suspend fun loadLocationData() {
        val countriesData = getCountryUseCase.execute()
        val citiesData = getCityUseCase.execute()
        val airportsData = getAirportUseCase.execute()

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
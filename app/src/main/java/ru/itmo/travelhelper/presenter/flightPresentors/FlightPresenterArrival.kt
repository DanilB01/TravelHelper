package ru.itmo.travelhelper.presenter.flightPresentors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flightTicketListRepositoriesImpl.LocationRepositoryImpl
import ru.itmo.domain.usecases.flightUseCases.GetAirportUseCase
import ru.itmo.domain.usecases.flightUseCases.GetCityUseCase
import ru.itmo.domain.usecases.flightUseCases.GetCountryUseCase
import ru.itmo.travelhelper.screens.flightScreens.FlightArrivalFragment

class FlightPresenterArrival(private val view: FlightArrivalFragment) {
    private val getCountryUseCase = GetCountryUseCase(LocationRepositoryImpl())
    private val getCityUseCase = GetCityUseCase(LocationRepositoryImpl())
    private val getAirportUseCase = GetAirportUseCase(LocationRepositoryImpl())
    private var savedArrivalData = mutableListOf("","","")


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
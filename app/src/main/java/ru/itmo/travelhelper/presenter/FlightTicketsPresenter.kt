package ru.itmo.travelhelper.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flightTicketListRepositoriesImpl.LocationRepositoryImpl
import ru.itmo.domain.usecases.flightTicketListUseCases.GetLocationUseCase
import ru.itmo.travelhelper.view.FlightTicketsView

class FlightTicketsPresenter(private val view: FlightTicketsView) {
    private val getLocationUseCase = GetLocationUseCase(LocationRepositoryImpl())

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
}
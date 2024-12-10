package ru.itmo.travelhelper.presenter.flight

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flight.LocationsRepositoryImpl
import ru.itmo.domain.usecases.flight.GetAirportUseCase
import ru.itmo.domain.usecases.flight.GetCityUseCase
import ru.itmo.domain.usecases.flight.GetCountryUseCase
import ru.itmo.travelhelper.view.flight.FlightArrivalView

class FlightArrivalPresenter(private val view: FlightArrivalView) {
    private val getCountryUseCase = GetCountryUseCase(LocationsRepositoryImpl())
    private val getCityUseCase = GetCityUseCase(LocationsRepositoryImpl())
    private val getAirportUseCase = GetAirportUseCase(LocationsRepositoryImpl())
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
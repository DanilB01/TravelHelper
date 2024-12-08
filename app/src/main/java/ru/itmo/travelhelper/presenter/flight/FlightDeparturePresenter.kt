package ru.itmo.travelhelper.presenter.flight

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.flight.LocationsRepositoryImpl
import ru.itmo.domain.usecases.flight.GetAirportUseCase
import ru.itmo.domain.usecases.flight.GetCityUseCase
import ru.itmo.domain.usecases.flight.GetCountryUseCase
import ru.itmo.travelhelper.view.flight.FlightDepartureView

class FlightDeparturePresenter(private val view: FlightDepartureView) {
    private val getCountryUseCase = GetCountryUseCase(LocationsRepositoryImpl())
    private val getCityUseCase = GetCityUseCase(LocationsRepositoryImpl())
    private val getAirportUseCase = GetAirportUseCase(LocationsRepositoryImpl())
    private var savedDepartureData = mutableListOf("","","")

    fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadLocationData()
        }
    }


    suspend fun loadLocationData() {
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
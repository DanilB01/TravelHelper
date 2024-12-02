package ru.itmo.travelhelper.view.flightViews

import ru.itmo.domain.models.flightModels.AirportModel
import ru.itmo.domain.models.flightModels.CityModel
import ru.itmo.domain.models.flightModels.CountryModel

interface FlightArrivalFragmentView {

    fun getAirportsByCityName(city_name: String): List<String>
    fun getCitiesByCountryName(country_name: String): List<String>
    fun getCountries(countriesData: List<CountryModel>)
    fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>)
    fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>)
    fun setSavedArrivalData(locationDataList: MutableList<String>)

}


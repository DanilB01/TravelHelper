package ru.itmo.travelhelper.view.flightViews

import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel

interface FlightDepartureFragmentView {

    fun getAirportsByCityName(city_name: String): List<String>
    fun getCitiesByCountryName(country_name: String): List<String>
    fun getCountries(countriesData: List<CountryModel>)
    fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>)
    fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>)
    fun setSavedDepartureData(locationDataList: MutableList<String>)

}


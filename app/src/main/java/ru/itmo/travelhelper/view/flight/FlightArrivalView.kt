package ru.itmo.travelhelper.view.flight

import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel

interface FlightArrivalView {

    fun getAirportsByCityName(city_name: String): List<String>
    fun getCitiesByCountryName(country_name: String): List<String>
    fun getCountries(countriesData: List<CountryModel>)
    fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>)
    fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>)
    fun setSavedArrivalData(locationDataList: MutableList<String>)

}


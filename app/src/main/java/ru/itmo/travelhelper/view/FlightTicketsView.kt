package ru.itmo.travelhelper.view

import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel

interface FlightTicketsView {

    fun getAirportsByCityName(city_name: String): List<String>
    fun getCitiesByCountryName(country_name: String): List<String>
    fun getCountries(countriesData: List<CountryModel>)
    fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>)
    fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>)


}

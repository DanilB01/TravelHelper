package ru.itmo.travelhelper.screens.flightScreens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationDepartureBinding
import ru.itmo.travelhelper.presenter.flightPresentors.FlightPresenterDeparture
import ru.itmo.travelhelper.view.flightViews.FlightDepartureFragmentView


class FlightDepartureFragment : Fragment(), FlightDepartureFragmentView {
    lateinit var binding: FragmentFlightLocationDepartureBinding
    private val presenter: FlightPresenterDeparture by lazy { FlightPresenterDeparture(this) }


    private lateinit var adapterCountryDep: ListAdapter
    private lateinit var adapterCityDep: ListAdapter
    private lateinit var adapterAirportDep: ListAdapter

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromDeparture", bundleOf("DepartureListData" to presenter.giveDepartureData()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightLocationDepartureBinding.inflate(inflater)
        presenter.setupView()
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightDepartureFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragmentManager.setFragmentResultListener("requestFlightToDepartureFromActivity", this) { _, result ->
            result.getStringArrayList("DepartureDataListFromAct")?.let { presenter.updateFullSavedDepartureData(it.toMutableList()) }
            setSavedDepartureData(presenter.giveDepartureData())

        }

        adapterCountryDep = ListAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("country", selectedItem)

                showNextBlockWhenOnClicked("city")

                presenter.updateExactIndexSavedDepartureData(selectedItem,0)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))

            }
        })

        binding.countryDepartureListView.adapter = adapterCountryDep

        binding.editTextSearchCountryDeparture.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString(), countries_data, adapterCountryDep)
            }
        })

        binding.clearCountryDepartureSearchFieldButton.setOnClickListener {
            binding.editTextSearchCountryDeparture.text.clear()
            filter("", countries_data, adapterCountryDep)
        }

        binding.countryDepartureLocationPickerButtonFlightTickets.setOnClickListener() {
            hideBlockOfView("city")
            hideBlockOfView("airport")

            showListAdapterWhenOnClicked("country")

            presenter.updateExactIndexSavedDepartureData("",0)
            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))

        }


        adapterCityDep = ListAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("city", selectedItem)

                showNextBlockWhenOnClicked("airport")

                presenter.updateExactIndexSavedDepartureData(selectedItem,1)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))
            }
        })

        binding.cityDepartureListView.adapter = adapterCityDep

        binding.editTextSearchCityDeparture.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val countryNameInButton: String = binding.countryDepartureLocationPickerButtonFlightTickets.text.toString()
                val currentCountryCities: List<String> = getCitiesByCountryName(countryNameInButton)
                filter(editable.toString(), currentCountryCities, adapterCityDep)
            }
        })

        binding.clearCityDepartureSearchFieldButton.setOnClickListener {
            binding.editTextSearchCityDeparture.text.clear()
            val countryNameInButton: String = binding.countryDepartureLocationPickerButtonFlightTickets.text.toString()
            val currentCountryCities: List<String> = cities_data[countryNameInButton]!!
            filter("", currentCountryCities, adapterCityDep)
        }

        binding.cityDepartureLocationPickerButtonFlightTickets.setOnClickListener() {
            hideBlockOfView("airport")

            showListAdapterWhenOnClicked("city")

            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))

        }


        adapterAirportDep = ListAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("airport", selectedItem)

                presenter.updateExactIndexSavedDepartureData(selectedItem,2)

                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to true))

            }

        })

        binding.airportDepartureListView.adapter = adapterAirportDep

        binding.editTextSearchAirportDeparture.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val cityNameInButton: String = binding.cityDepartureLocationPickerButtonFlightTickets.text.toString()
                val currentCityAirports: List<String> = getAirportsByCityName(cityNameInButton)
                filter(editable.toString(), currentCityAirports, adapterAirportDep)
            }
        })

        binding.clearAirportDepartureSearchFieldButton.setOnClickListener {
            binding.editTextSearchAirportDeparture.text.clear()
            val cityNameInButton: String = binding.cityDepartureLocationPickerButtonFlightTickets.text.toString()
            val currentCityAirports: List<String> = airports_data[cityNameInButton]!!
            filter("", currentCityAirports, adapterAirportDep)
        }

        binding.airportDepartureLocationPickerButtonFlightTickets.setOnClickListener() {
            showListAdapterWhenOnClicked("airport")

            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))
        }

    }


    private fun filter(text: String, list_data: List<String>, adapterName: UpdateListInterface) {
        val filteredList = mutableListOf<String>()

        if (text.isNotEmpty()) {
            for (item in list_data) {
                if (item.contains(text, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
        }

        adapterName.updateList(filteredList)
    }

    override fun getCountries(countriesData: List<CountryModel>) {
        this.countries_data = countriesData
            .map { country -> country.countryName }
    }

    override fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>) {
        this.cities_data = citiesData
            .mapValues { entry -> entry.value.map { city -> city.cityName } }
            .mapKeys { keyName -> keyName.key.countryName }
    }

    override fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>) {
        this.airports_data = airportsData
            .mapValues { entry -> entry.value.map { airport -> airport.airportName } }
            .mapKeys { keyName -> keyName.key.cityName }
    }


    override fun setSavedDepartureData(locationDataList: MutableList<String>) {
        if (locationDataList.all { it.isNotEmpty() }) {
            binding.countryDepartureLocationPickerButtonFlightTickets.text = locationDataList[0]
            binding.countryDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.countryDepartureListView.visibility = View.VISIBLE
            binding.countryDepartureTitleText.text = "Страна"

            binding.cityDepartureLocationPickerButtonFlightTickets.text = locationDataList[1]
            binding.cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.cityDepartureTitleText.visibility = View.VISIBLE
            binding.cityDepartureTitleText.text = "Город"

            binding.airportDepartureLocationPickerButtonFlightTickets.text = locationDataList[2]
            binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.airportDepartureTitleText.visibility = View.VISIBLE
            binding.airportDepartureTitleText.text = "Аэропорт"

        }
    }


    override fun getAirportsByCityName(city_name: String): List<String> {
        return airports_data[city_name]!!
    }

    override fun getCitiesByCountryName(country_name: String): List<String> {
        return cities_data[country_name]!!
    }

    private fun hideBlockOfView(typeView: String) {
        when (typeView) {
            "city" -> {
                with(binding) {
                    cityDepartureTitleText.visibility = View.GONE
                    cityDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutCityDepartureSearchField.visibility = View.GONE }
            }
            "airport" -> {
                with(binding) {
                    airportDepartureTitleText.visibility = View.GONE
                    airportDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutAirportDepartureSearchField.visibility = View.GONE }
            }
        }
    }


    private fun showBlockWhenSetSaving(typeView: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    countryDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    countryDepartureTitleText.visibility = View.VISIBLE
                    countryDepartureTitleText.text = "Страна" }
            }
            "city" -> {
                with(binding) {
                    cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    cityDepartureTitleText.visibility = View.VISIBLE
                    cityDepartureTitleText.text = "Город" }
            }
            "airport" -> {
                with(binding) {
                    airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    airportDepartureTitleText.visibility = View.VISIBLE
                    airportDepartureTitleText.text = "Аэропорт" }
            }
        }
    }

    private fun showBlockWhenOnClicked(typeView: String, selectedItem: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    layoutCountryDepartureSearchField.visibility = View.GONE
                    countryDepartureLocationPickerButtonFlightTickets.text = selectedItem
                    countryDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    countryDepartureTitleText.text = "Страна" }
            }
            "city" -> {
                with(binding) {
                    layoutCityDepartureSearchField.visibility = View.GONE
                    cityDepartureLocationPickerButtonFlightTickets.text = selectedItem
                    cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    cityDepartureTitleText.text = "Город" }
            }
            "airport" -> {
                with(binding) {
                    layoutAirportDepartureSearchField.visibility = View.GONE
                    airportDepartureLocationPickerButtonFlightTickets.text = selectedItem
                    airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    airportDepartureTitleText.text = "Аэропорт" }
            }
        }
    }


    private fun showListAdapterWhenOnClicked(typeView: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    countryDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCountryDeparture.text.clear()
                    layoutCountryDepartureSearchField.visibility = View.VISIBLE
                    countryDepartureTitleText.text = "Выберите страну" }
            }
            "city" -> {
                with(binding) {
                    cityDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCityDeparture.text.clear()
                    layoutCityDepartureSearchField.visibility = View.VISIBLE
                    cityDepartureTitleText.text = "Выберите город" }
            }
            "airport" -> {
                with(binding) {
                    airportDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchAirportDeparture.text.clear()
                    layoutAirportDepartureSearchField.visibility = View.VISIBLE
                    airportDepartureTitleText.text = "Выберите аэропорт" }
            }
        }
    }

    private fun showNextBlockWhenOnClicked(typeView: String) {
        when (typeView) {
            "city" -> {
                with(binding) {
                    cityDepartureTitleText.text = "Выберите город"
                    cityDepartureTitleText.visibility = View.VISIBLE
                    cityDepartureLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                    cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE }
            }
            "airport" -> {
                with(binding) {
                    airportDepartureTitleText.text = "Выберите аэропорт"
                    airportDepartureTitleText.visibility = View.VISIBLE
                    airportDepartureLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                    airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE }
            }
        }
    }


}





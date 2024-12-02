package ru.itmo.travelhelper.screens.flight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationArrivalBinding
import ru.itmo.travelhelper.presenter.flight.FlightArrivalPresenter
import ru.itmo.travelhelper.view.flight.FlightArrivalView


class FlightArrivalFragment() : Fragment(), FlightArrivalView {
    lateinit var binding: FragmentFlightLocationArrivalBinding
    private val presenter: FlightArrivalPresenter by lazy { FlightArrivalPresenter(this ) }


    private lateinit var adapterCountryArr: FlightListAdapterFlight
    private lateinit var adapterCityArr: FlightListAdapterFlight
    private lateinit var adapterAirportArr: FlightListAdapterFlight

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>


    var localDepartureDataList: MutableList<String> = mutableListOf("","","")


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrival", bundleOf("ArrivalListData" to presenter.giveArrivalData()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightLocationArrivalBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightArrivalFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setupView()
        parentFragmentManager.setFragmentResultListener("requestFlightToArrivalFromActivity", this) { _, result ->
            localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            result.getStringArrayList("ArrivalDataListFromAct")?.let { presenter.updateFullSavedArrivalData(it.toMutableList()) }
            setSavedArrivalData(presenter.giveArrivalData())
        }


        adapterCountryArr = FlightListAdapterFlight(emptyList(), object : FlightOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("country", selectedItem)

                showNextBlockWhenOnClicked("city")

                presenter.updateExactIndexSavedArrivalData(selectedItem,0)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))

            }

        })

        binding.countryArrivalListView.adapter = adapterCountryArr

        binding.editTextSearchCountryArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString(), countries_data, adapterCountryArr, 0)
            }
        })

        binding.clearCountryArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchCountryArrival.text.clear()
            filter("", countries_data, adapterCountryArr, 0)
        }

        binding.countryArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            hideBlockOfView("city")
            hideBlockOfView("airport")

            showListAdapterWhenOnClicked("country")

            presenter.updateExactIndexSavedArrivalData("",0)
            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))

        }


        adapterCityArr = FlightListAdapterFlight(emptyList(), object : FlightOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("city", selectedItem)

                showNextBlockWhenOnClicked("airport")

                presenter.updateExactIndexSavedArrivalData(selectedItem,1)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))
            }
        })

        binding.cityArrivalListView.adapter = adapterCityArr

        binding.editTextSearchCityArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val countryNameInButton: String = binding.countryArrivalLocationPickerButtonFlightTickets.text.toString()
                val currentCountryCities: List<String> = getCitiesByCountryName(countryNameInButton)
                filter(editable.toString(), currentCountryCities, adapterCityArr, 1)
            }
        })

        binding.clearCityArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchCityArrival.text.clear()
            val countryNameInButton: String = binding.countryArrivalLocationPickerButtonFlightTickets.text.toString()
            val currentCountryCities: List<String> = getCitiesByCountryName(countryNameInButton)
            filter("", currentCountryCities, adapterCityArr, 1)
        }

        binding.cityArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            hideBlockOfView("airport")

            showListAdapterWhenOnClicked("city")


            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))


        }



        adapterAirportArr = FlightListAdapterFlight(emptyList(), object : FlightOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("airport", selectedItem)

                presenter.updateExactIndexSavedArrivalData(selectedItem,2)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to true))
            }

        })

        binding.airportArrivalListView.adapter = adapterAirportArr

        binding.editTextSearchAirportArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val cityNameInButton: String = binding.cityArrivalLocationPickerButtonFlightTickets.text.toString()
                val currentCityAirports: List<String> = getAirportsByCityName(cityNameInButton)
                filter(editable.toString(), currentCityAirports, adapterAirportArr, 2)
            }
        })

        binding.clearAirportArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchAirportArrival.text.clear()
            val cityNameInButton: String = binding.cityArrivalLocationPickerButtonFlightTickets.text.toString()
            val currentCityAirports: List<String> = getAirportsByCityName(cityNameInButton)
            filter("", currentCityAirports, adapterAirportArr, 2)
        }

        binding.airportArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            showListAdapterWhenOnClicked("airport")


            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))
        }

    }


    private fun filter(text: String, list_data: List<String>, adapterName: FlightUpdateListInterface, location_data_id: Int) {
        val filteredList = mutableListOf<String>()
        val DepartureDataList = localDepartureDataList
        val ArrivalDataList = presenter.giveArrivalData()
        val checkingWithDepartureData: String = DepartureDataList[location_data_id]
        if (text.isNotEmpty()) {
            for (item in list_data) {

                if (item.contains(text, ignoreCase = true) &&
                    !(location_data_id == 1 && item == checkingWithDepartureData && ArrivalDataList.get(0) == DepartureDataList[0])) {
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



    override fun getAirportsByCityName(city_name: String): List<String> {
        return airports_data[city_name]!!
    }

    override fun getCitiesByCountryName(country_name: String): List<String> {
        return cities_data[country_name]!!
    }

    override fun setSavedArrivalData(locationDataList: MutableList<String>) {
        val DepartureDataList = localDepartureDataList

        showBlockWhenSetSaving("country")

        if (locationDataList[0] != "") {
            binding.countryArrivalLocationPickerButtonFlightTickets.text = locationDataList[0]

            showBlockWhenSetSaving("city")

            if (locationDataList[1] != "" &&
                !(DepartureDataList[1] == locationDataList[1] &&
                        DepartureDataList[0] == locationDataList[0])) {

                binding.cityArrivalLocationPickerButtonFlightTickets.text = locationDataList[1]

                showBlockWhenSetSaving("airport")

                if (locationDataList[2] != "") {
                    binding.airportArrivalLocationPickerButtonFlightTickets.text = locationDataList[2]
                }
                else {
                    binding.airportArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                    presenter.updateExactIndexSavedArrivalData("",2)
                }

            }
            else {
                binding.cityArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                presenter.updateExactIndexSavedArrivalData("",1)
                presenter.updateExactIndexSavedArrivalData("",2)
            }
        }
        else {
            binding.countryArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать страну"

            presenter.updateExactIndexSavedArrivalData("",0)
            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
        }

    }

    private fun hideBlockOfView(typeView: String) {
        when (typeView) {
            "city" -> {
                with(binding) {
                    cityArrivalTitleText.visibility = View.GONE
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutCityArrivalSearchField.visibility = View.GONE }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalTitleText.visibility = View.GONE
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutAirportArrivalSearchField.visibility = View.GONE }
            }
        }
    }


    private fun showBlockWhenSetSaving(typeView: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    countryArrivalTitleText.visibility = View.VISIBLE
                    countryArrivalTitleText.text = "Страна" }
            }
            "city" -> {
                with(binding) {
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    cityArrivalTitleText.visibility = View.VISIBLE
                    cityArrivalTitleText.text = "Город" }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    airportArrivalTitleText.visibility = View.VISIBLE
                    airportArrivalTitleText.text = "Аэропорт" }
            }
        }
    }

    private fun showBlockWhenOnClicked(typeView: String, selectedItem: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    layoutCountryArrivalSearchField.visibility = View.GONE
                    countryArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    countryArrivalTitleText.text = "Страна" }
            }
            "city" -> {
                with(binding) {
                    layoutCityArrivalSearchField.visibility = View.GONE
                    cityArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    cityArrivalTitleText.text = "Город" }
            }
            "airport" -> {
                with(binding) {
                    layoutAirportArrivalSearchField.visibility = View.GONE
                    airportArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    airportArrivalTitleText.text = "Аэропорт" }
            }
        }
    }


    private fun showListAdapterWhenOnClicked(typeView: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    countryArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCountryArrival.text.clear()
                    layoutCountryArrivalSearchField.visibility = View.VISIBLE
                    countryArrivalTitleText.text = "Выберите страну" }
            }
            "city" -> {
                with(binding) {
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCityArrival.text.clear()
                    layoutCityArrivalSearchField.visibility = View.VISIBLE
                    cityArrivalTitleText.text = "Выберите город" }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchAirportArrival.text.clear()
                    layoutAirportArrivalSearchField.visibility = View.VISIBLE
                    airportArrivalTitleText.text = "Выберите аэропорт" }
            }
        }
    }

    private fun showNextBlockWhenOnClicked(typeView: String) {
        when (typeView) {
            "city" -> {
                with(binding) {
                    cityArrivalTitleText.text = "Выберите город"
                    cityArrivalTitleText.visibility = View.VISIBLE
                    cityArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalTitleText.text = "Выберите аэропорт"
                    airportArrivalTitleText.visibility = View.VISIBLE
                    airportArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE }
            }
        }
    }

}


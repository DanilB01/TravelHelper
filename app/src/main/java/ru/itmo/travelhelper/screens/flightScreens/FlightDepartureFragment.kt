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
                binding.layoutCountryDepartureSearchField.visibility = View.GONE
                binding.countryDepartureLocationPickerButtonFlightTickets.text = selectedItem
                binding.countryDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.countryDepartureTitleText.text = "Страна"

                binding.cityDepartureTitleText.text = "Выберите город"
                binding.cityDepartureTitleText.visibility = View.VISIBLE
                binding.cityDepartureLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                binding.cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE

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
            binding.cityDepartureTitleText.visibility = View.GONE
            binding.cityDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutCityDepartureSearchField.visibility = View.GONE


            binding.airportDepartureTitleText.visibility = View.GONE
            binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportDepartureSearchField.visibility = View.GONE


            binding.countryDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCountryDeparture.text.clear()
            binding.layoutCountryDepartureSearchField.visibility = View.VISIBLE
            binding.countryDepartureTitleText.text = "Выберите страну"

            presenter.updateExactIndexSavedDepartureData("",0)
            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))

        }


        adapterCityDep = ListAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutCityDepartureSearchField.visibility = View.GONE
                binding.cityDepartureLocationPickerButtonFlightTickets.text = selectedItem
                binding.cityDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.cityDepartureTitleText.text = "Город"

                binding.airportDepartureTitleText.text = "Выберите аэропорт"
                binding.airportDepartureTitleText.visibility = View.VISIBLE
                binding.airportDepartureLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE

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
            binding.airportDepartureTitleText.visibility = View.GONE
            binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportDepartureSearchField.visibility = View.GONE

            binding.cityDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCityDeparture.text.clear()
            binding.layoutCityDepartureSearchField.visibility = View.VISIBLE
            binding.cityDepartureTitleText.text = "Выберите город"

            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool", bundleOf("isDepartureFull" to false))

        }


        adapterAirportDep = ListAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutAirportDepartureSearchField.visibility = View.GONE
                binding.airportDepartureLocationPickerButtonFlightTickets.text = selectedItem
                binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.airportDepartureTitleText.text = "Аэропорт"

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
            binding.airportDepartureLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchAirportDeparture.text.clear()
            binding.layoutAirportDepartureSearchField.visibility = View.VISIBLE
            binding.airportDepartureTitleText.text = "Выберите аэропорт"

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


}





package ru.itmo.travelhelper.screens.flight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationDepartureBinding
import ru.itmo.travelhelper.presenter.flight.FlightDeparturePresenter
import ru.itmo.travelhelper.view.flight.FlightDepartureView


class FlightDepartureFragment : Fragment(), FlightDepartureView {
    lateinit var binding: FragmentFlightLocationDepartureBinding
    private val presenter: FlightDeparturePresenter by lazy { FlightDeparturePresenter(this) }


    private lateinit var adapterCountryDep: FlightLocationsListAdapter
    private lateinit var adapterCityDep: FlightLocationsListAdapter
    private lateinit var adapterAirportDep: FlightLocationsListAdapter

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные в активити при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromDeparture", bundleOf("DepartureListData" to presenter.giveDepartureData()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Подгрузка данных с data
        presenter.setupView()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightLocationDepartureBinding.inflate(inflater)
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightDepartureFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Получение данных с активити
        parentFragmentManager.setFragmentResultListener("requestFlightToDepartureFromActivity", this) { _, result ->
            result.getStringArrayList("DepartureDataListFromAct")?.let { presenter.updateFullSavedDepartureData(it.toMutableList()) }
            setSavedDepartureData(presenter.giveDepartureData())
        }

        //Адаптер для страны

        launchCountryAdapter()

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
            adapterCountryDep.updateList(countries_data.toMutableList())

            presenter.updateExactIndexSavedDepartureData("",0)
            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                bundleOf("isDepartureFull" to false))

        }

        //Адаптер для города

        launchCityAdapter()

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
            adapterCityDep.updateList(cities_data[presenter.giveDepartureData()[0]]?.toMutableList()
                ?: mutableListOf("ERROR DATA"))

            presenter.updateExactIndexSavedDepartureData("",1)
            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                bundleOf("isDepartureFull" to false))

        }

        //Адаптер для аэропорта

        launchAirportAdapter()

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
            adapterAirportDep.updateList(airports_data[presenter.giveDepartureData()[1]]?.toMutableList()
                ?: mutableListOf("ERROR DATA"))

            presenter.updateExactIndexSavedDepartureData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                bundleOf("isDepartureFull" to false))
        }

    }



    //Функция создания адаптера для страны
    private fun launchCountryAdapter() {
        adapterCountryDep = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("country", selectedItem)

                showNextBlockWhenOnClicked("city")


                presenter.updateExactIndexSavedDepartureData(selectedItem,0)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                    bundleOf("isDepartureFull" to false))

            }
        })

        binding.countryDepartureListView.adapter = adapterCountryDep
    }

    //Функция создания адаптера для города
    private fun launchCityAdapter() {
        adapterCityDep = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("city", selectedItem)

                showNextBlockWhenOnClicked("airport")

                presenter.updateExactIndexSavedDepartureData(selectedItem,1)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                    bundleOf("isDepartureFull" to false))
            }
        })

        binding.cityDepartureListView.adapter = adapterCityDep
    }

    //Функция создания адаптера для аэропорта
    private fun launchAirportAdapter() {
        adapterAirportDep = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                showBlockWhenOnClicked("airport", selectedItem)

                presenter.updateExactIndexSavedDepartureData(selectedItem,2)

                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDepartureBool",
                    bundleOf("isDepartureFull" to true))

            }

        })

        binding.airportDepartureListView.adapter = adapterAirportDep
    }


    //Функция фильтрации текста в адаптере
    private fun filter(text: String, list_data: List<String>, adapterName: FlightLocationsUpdateListInterface) {
        val filteredList = mutableListOf<String>()

        for (item in list_data) {
            if (item.contains(text, ignoreCase = true)) {
                    filteredList.add(item)
            }
        }


        adapterName.updateList(filteredList)
    }

    //Функция получения списка стран
    override fun getCountries(countriesData: List<CountryModel>) {
        this.countries_data = countriesData
            .map { country -> country.countryName }
    }

    //Функция получения словаря городов
    override fun getCitiesMap(citiesData: Map<CountryModel, List<CityModel>>) {
        this.cities_data = citiesData
            .mapValues { entry -> entry.value.map { city -> city.cityName } }
            .mapKeys { keyName -> keyName.key.countryName }
    }

    //Функция получения словаря аэропортов
    override fun getAirportMap(airportsData: Map<CityModel, List<AirportModel>>) {
        this.airports_data = airportsData
            .mapValues { entry -> entry.value.map { airport -> airport.airportName } }
            .mapKeys { keyName -> keyName.key.cityName }
    }

    //Функция установки сохраненных данных вылета
    override fun setSavedDepartureData(locationDataList: MutableList<String>) {
        if (locationDataList.all { it.isNotEmpty() }) {
            binding.countryDepartureLocationPickerButtonFlightTickets.text = locationDataList[0]
            binding.countryDepartureLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.countryDepartureTitleText.visibility = View.VISIBLE
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

    //Функция получения списка аэропортов по имени города
    override fun getAirportsByCityName(city_name: String): List<String> {
        return airports_data[city_name]!!
    }

    //Функция получения списка городов по имени страны
    override fun getCitiesByCountryName(country_name: String): List<String> {
        return cities_data[country_name]!!
    }


    //Функция скрытия блока View
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

    //Функция отображения текущего блока при клике на текущий
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

    //Функция отображения адаптера при клике
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

    //Функция отображения следующего блока при клике на текущий
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





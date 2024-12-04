package ru.itmo.travelhelper.screens.flight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationArrivalBinding
import ru.itmo.travelhelper.presenter.flight.FlightArrivalPresenter
import ru.itmo.travelhelper.view.flight.FlightArrivalView


class FlightArrivalFragment() : Fragment(), FlightArrivalView {
    lateinit var binding: FragmentFlightLocationArrivalBinding
    private val presenter: FlightArrivalPresenter by lazy { FlightArrivalPresenter(this ) }


    private lateinit var adapterCountryArr: FlightLocationsListAdapter
    private lateinit var adapterCityArr: FlightLocationsListAdapter
    private lateinit var adapterAirportArr: FlightLocationsListAdapter

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>


    var localDepartureDataList: MutableList<String> = mutableListOf("","","")


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные в активити при закрытии фрагмента

        parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrival", bundleOf("ArrivalListData" to presenter.giveArrivalData()))
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
        binding = FragmentFlightLocationArrivalBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightArrivalFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {




        //Получение данных с активити

        parentFragmentManager.setFragmentResultListener("requestFlightToArrivalFromActivity", this) { _, result ->
            this.localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            result.getStringArrayList("ArrivalDataListFromAct")?.let { presenter.updateFullSavedArrivalData(it.toMutableList()) }
            setSavedArrivalData(presenter.giveArrivalData())
        }

        //Адаптер для страны

        launchCountryAdapter()

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
            filter("", countries_data, adapterCountryArr, 0)


            presenter.updateExactIndexSavedArrivalData("",0)
            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                bundleOf("isArrivalFull" to false))

        }

        //Адаптер для города

        launchCityAdapter()

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
            filter("", cities_data[presenter.giveArrivalData()[0]]!!, adapterCityArr, 1)


            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                bundleOf("isArrivalFull" to false))

        }

        //Адаптер для аэропорта

        launchAirportAdapter()

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

        binding.airportArrivalLocationPickerButtonFlightTickets.setOnClickListener {

            showListAdapterWhenOnClicked("airport")
            filter("", airports_data[presenter.giveArrivalData()[1]]!!, adapterAirportArr, 2)

            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                bundleOf("isArrivalFull" to false))
        }

    }


    //Функция создания адаптера для страны
    private fun launchCountryAdapter() {
        binding.countryArrivalListView.layoutManager = LinearLayoutManager(context)
        adapterCountryArr = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {
                showBlockWhenOnClicked("country", adapterCountryArr.items[selectedItem])

                showNextBlockWhenOnClicked("city")

                presenter.updateExactIndexSavedArrivalData(adapterCountryArr.items[selectedItem],0)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                    bundleOf("isArrivalFull" to false))
            }
        })

        binding.countryArrivalListView.adapter = adapterCountryArr
    }

    //Функция создания адаптера для города
    private fun launchCityAdapter() {
        binding.cityArrivalListView.layoutManager = LinearLayoutManager(context)
        adapterCityArr = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {
                showBlockWhenOnClicked("city", adapterCityArr.items[selectedItem])

                showNextBlockWhenOnClicked("airport")

                presenter.updateExactIndexSavedArrivalData(adapterCityArr.items[selectedItem],1)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                    bundleOf("isArrivalFull" to false))
            }
        })

        binding.cityArrivalListView.adapter = adapterCityArr
    }

    //Функция создания адаптера для аэропорта
    private fun launchAirportAdapter() {
        binding.airportArrivalListView.layoutManager = LinearLayoutManager(context)
        adapterAirportArr = FlightLocationsListAdapter(emptyList(), object : FlightLocationsOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {
                showBlockWhenOnClicked("airport", adapterAirportArr.items[selectedItem])

                presenter.updateExactIndexSavedArrivalData(adapterAirportArr.items[selectedItem],2)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool",
                    bundleOf("isArrivalFull" to true))
            }

        })

        binding.airportArrivalListView.adapter = adapterAirportArr
    }

    //Функция скрытия блока View
    private fun hideBlockOfView(typeView: String) {
        when (typeView) {
            "city" -> {
                with(binding) {
                    cityArrivalTitleText.visibility = View.GONE
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutCityArrivalSearchField.visibility = View.GONE
                    cityArrivalListView.visibility = View.GONE }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalTitleText.visibility = View.GONE
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    layoutAirportArrivalSearchField.visibility = View.GONE
                    airportArrivalListView.visibility = View.GONE }
            }
        }
    }

    //Функция отображения блока при установке сохраненных данных
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

    //Функция отображения текущего блока при клике на текущий
    private fun showBlockWhenOnClicked(typeView: String, selectedItem: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    layoutCountryArrivalSearchField.visibility = View.GONE
                    countryArrivalListView.visibility = View.GONE
                    countryArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    countryArrivalTitleText.text = "Страна" }
            }
            "city" -> {
                with(binding) {
                    layoutCityArrivalSearchField.visibility = View.GONE
                    cityArrivalListView.visibility = View.GONE
                    cityArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    cityArrivalTitleText.text = "Город" }
            }
            "airport" -> {
                with(binding) {
                    layoutAirportArrivalSearchField.visibility = View.GONE
                    airportArrivalListView.visibility = View.GONE
                    airportArrivalLocationPickerButtonFlightTickets.text = selectedItem
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    airportArrivalTitleText.text = "Аэропорт" }
            }
        }
    }

    //Функция отображения адаптера при клике
    private fun showListAdapterWhenOnClicked(typeView: String) {
        when (typeView) {
            "country" -> {
                with(binding) {
                    countryArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCountryArrival.text.clear()
                    layoutCountryArrivalSearchField.visibility = View.VISIBLE
                    countryArrivalListView.visibility = View.VISIBLE
                    countryArrivalTitleText.text = "Выберите страну" }
            }
            "city" -> {
                with(binding) {
                    cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchCityArrival.text.clear()
                    layoutCityArrivalSearchField.visibility = View.VISIBLE
                    cityArrivalListView.visibility = View.VISIBLE
                    cityArrivalTitleText.text = "Выберите город" }
            }
            "airport" -> {
                with(binding) {
                    airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
                    editTextSearchAirportArrival.text.clear()
                    layoutAirportArrivalSearchField.visibility = View.VISIBLE
                    airportArrivalListView.visibility = View.VISIBLE
                    airportArrivalTitleText.text = "Выберите аэропорт" }
            }
        }
    }

    //Функция отображения следующего блока при клике на текущий
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


    // Функция фильтрации текста в адаптере
    private fun filter(text: String, list_data: List<String>, adapterName: FlightLocationsUpdateListInterface, location_data_id: Int) {
        val filteredList = mutableListOf<String>()
        val DepartureDataList = this.localDepartureDataList
        val ArrivalDataList = presenter.giveArrivalData()
        val checkingWithDepartureData: String = DepartureDataList[location_data_id]

        for (item in list_data) {
            if (item.contains(text, ignoreCase = true) &&
                !(location_data_id == 1 && item == checkingWithDepartureData && ArrivalDataList[0] == DepartureDataList[0])) {
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

    //Функция получения списка аэропортов по имени города
    override fun getAirportsByCityName(city_name: String): List<String> {
        return airports_data[city_name]!!
    }

    //Функция получения списка городов по имени страны
    override fun getCitiesByCountryName(country_name: String): List<String> {
        return cities_data[country_name]!!
    }

    //Функция установки сохраненных данных прилета
    override fun setSavedArrivalData(locationDataList: MutableList<String>) {
        val DepartureDataList = this.localDepartureDataList

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





}


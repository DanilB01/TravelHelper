package ru.itmo.travelhelper.screens.flightScreens

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationArrivalBinding
import ru.itmo.travelhelper.presenter.flightPresentors.FlightPresenterArrivalFragment
import ru.itmo.travelhelper.view.flightViews.FlightArrivalFragmentView


class FlightArrivalFragment() : Fragment(), FlightArrivalFragmentView {
    lateinit var binding: FragmentFlightLocationArrivalBinding
    private val presenter: FlightPresenterArrivalFragment by lazy { FlightPresenterArrivalFragment(this ) }


    private lateinit var adapterCountry: ListAdapterArrival
    private lateinit var adapterCity: ListAdapterArrival
    private lateinit var adapterAirport: ListAdapterArrival

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







        adapterCountry = ListAdapterArrival(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutCountryArrivalSearchField.visibility = View.GONE
                binding.countryArrivalLocationPickerButtonFlightTickets.text = selectedItem
                binding.countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.countryArrivalTitleText.text = "Страна"

                binding.cityArrivalTitleText.text = "Выберите город"
                binding.cityArrivalTitleText.visibility = View.VISIBLE
                binding.cityArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE

                presenter.updateExactIndexSavedArrivalData(selectedItem,0)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))

            }

        })

        binding.countryArrivalListView.adapter = adapterCountry

        binding.editTextSearchCountryArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString(), countries_data, adapterCountry, 0)
            }
        })

        binding.clearCountryArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchCountryArrival.text.clear()
            filter("", countries_data, adapterCountry, 0)
        }

        binding.countryArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.cityArrivalTitleText.visibility = View.GONE
            binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutCityArrivalSearchField.visibility = View.GONE


            binding.airportArrivalTitleText.visibility = View.GONE
            binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportArrivalSearchField.visibility = View.GONE


            binding.countryArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCountryArrival.text.clear()
            binding.layoutCountryArrivalSearchField.visibility = View.VISIBLE
            binding.countryArrivalTitleText.text = "Выберите страну"


            presenter.updateExactIndexSavedArrivalData("",0)
            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))

        }


        adapterCity = ListAdapterArrival(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutCityArrivalSearchField.visibility = View.GONE
                binding.cityArrivalLocationPickerButtonFlightTickets.text = selectedItem
                binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.cityArrivalTitleText.text = "Город"

                binding.airportArrivalTitleText.text = "Выберите аэропорт"
                binding.airportArrivalTitleText.visibility = View.VISIBLE
                binding.airportArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE

                presenter.updateExactIndexSavedArrivalData(selectedItem,1)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))
            }
        })

        binding.cityArrivalListView.adapter = adapterCity

        binding.editTextSearchCityArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val countryNameInButton: String = binding.countryArrivalLocationPickerButtonFlightTickets.text.toString()
                val currentCountryCities: List<String> = getCitiesByCountryName(countryNameInButton)
                filter(editable.toString(), currentCountryCities, adapterCity, 1)
            }
        })

        binding.clearCityArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchCityArrival.text.clear()
            val countryNameInButton: String = binding.countryArrivalLocationPickerButtonFlightTickets.text.toString()
            val currentCountryCities: List<String> = cities_data[countryNameInButton]!!
            filter("", currentCountryCities, adapterCity, 1)
        }

        binding.cityArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.airportArrivalTitleText.visibility = View.GONE
            binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportArrivalSearchField.visibility = View.GONE

            binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCityArrival.text.clear()
            binding.layoutCityArrivalSearchField.visibility = View.VISIBLE
            binding.cityArrivalTitleText.text = "Выберите город"


            presenter.updateExactIndexSavedArrivalData("",1)
            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))


        }



        adapterAirport = ListAdapterArrival(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutAirportArrivalSearchField.visibility = View.GONE
                binding.airportArrivalLocationPickerButtonFlightTickets.text = selectedItem
                binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.airportArrivalTitleText.text = "Аэропорт"

                presenter.updateExactIndexSavedArrivalData(selectedItem,2)
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to true))
            }

        })

        binding.airportArrivalListView.adapter = adapterAirport

        binding.editTextSearchAirportArrival.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val cityNameInButton: String = binding.cityArrivalLocationPickerButtonFlightTickets.text.toString()
                val currentCityAirports: List<String> = getAirportsByCityName(cityNameInButton)
                filter(editable.toString(), currentCityAirports, adapterAirport, 2)
            }
        })

        binding.clearAirportArrivalSearchFieldButton.setOnClickListener {
            binding.editTextSearchAirportArrival.text.clear()
            val cityNameInButton: String = binding.cityArrivalLocationPickerButtonFlightTickets.text.toString()
            val currentCityAirports: List<String> = airports_data[cityNameInButton]!!
            filter("", currentCityAirports, adapterAirport, 2)
        }

        binding.airportArrivalLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchAirportArrival.text.clear()
            binding.layoutAirportArrivalSearchField.visibility = View.VISIBLE
            binding.airportArrivalTitleText.text = "Выберите аэропорт"


            presenter.updateExactIndexSavedArrivalData("",2)
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromArrivalBool", bundleOf("isArrivalFull" to false))
        }

    }


    interface OnItemClickListener {
        fun onItemClicked(selectedItem: String)
    }


    private fun filter(text: String, list_data: List<String>, adapterName: UpdateListInterfaceArrival, location_data_id: Int) {
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

        binding.countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
        binding.countryArrivalTitleText.visibility = View.VISIBLE
        binding.countryArrivalTitleText.text = "Страна"

        if (locationDataList[0] != "") {
            binding.countryArrivalLocationPickerButtonFlightTickets.text = locationDataList[0]


            binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.cityArrivalTitleText.visibility = View.VISIBLE
            binding.cityArrivalTitleText.text = "Город"

            if (locationDataList[1] != "" &&
                !(DepartureDataList[1] == locationDataList[1] &&
                        DepartureDataList[0] == locationDataList[0])) {

                binding.cityArrivalLocationPickerButtonFlightTickets.text = locationDataList[1]

                binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.airportArrivalTitleText.visibility = View.VISIBLE
                binding.airportArrivalTitleText.text = "Аэропорт"

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




interface UpdateListInterfaceArrival {
    fun updateList(filteredItems: MutableList<String>)
}

class ListAdapterArrival(
    context: FlightArrivalFragment,
    private val items: List<String>,
    private val itemClickListener: FlightArrivalFragment.OnItemClickListener
) : BaseAdapter(), UpdateListInterfaceArrival {

    private var filteredItems = items.toMutableList()

    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun updateList(filteredItems: MutableList<String>) {
        this.filteredItems = filteredItems
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = if (convertView == null) {
            LayoutInflater.from(parent?.context).inflate(R.layout.simple_list_item_1, parent, false) as TextView
        } else {
            convertView as TextView
        }


        view.setOnClickListener {
            itemClickListener.onItemClicked(filteredItems[position])
        }

        view.text = filteredItems[position]
        return view
    }
}
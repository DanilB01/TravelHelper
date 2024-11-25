package ru.itmo.travelhelper.screens

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.travelhelper.databinding.ActivityFlightTicketsBinding
import ru.itmo.travelhelper.presenter.FlightTicketsPresenter
import ru.itmo.travelhelper.view.FlightTicketsView

class FlightTicketsActivity() : AppCompatActivity(), FlightTicketsView {


    private val binding by lazy { ActivityFlightTicketsBinding.inflate(layoutInflater) }
    private val presenter: FlightTicketsPresenter by lazy { FlightTicketsPresenter(this) }

    private lateinit var adapterCountry: ListAdapter
    private lateinit var adapterCity: ListAdapter
    private lateinit var adapterAirport: ListAdapter

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.setupView()




        adapterCountry = ListAdapter(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutCountrySearchField.visibility = View.GONE
                binding.countyLocationPickerButtonFlightTickets.text = selectedItem
                binding.countyLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.countryTitleText.text = "Страна"

                binding.cityTitleText.text = "Выберите город"
                binding.cityTitleText.visibility = View.VISIBLE
                binding.cityLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                binding.cityLocationPickerButtonFlightTickets.visibility = View.VISIBLE


            }
        })

        binding.countryListView.adapter = adapterCountry

        binding.editTextSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString(), countries_data, adapterCountry)
            }
        })

        binding.clearCountrySearchFieldButton.setOnClickListener {
            binding.editTextSearchCountry.text.clear()
            filter("", countries_data, adapterCountry)
        }

        binding.countyLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.cityTitleText.visibility = View.GONE
            binding.cityLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutCitySearchField.visibility = View.GONE


            binding.airportTitleText.visibility = View.GONE
            binding.airportLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportSearchField.visibility = View.GONE


            binding.countyLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCountry.text.clear()
            binding.layoutCountrySearchField.visibility = View.VISIBLE
            binding.countryTitleText.text = "Выберите страну"

        }







        adapterCity = ListAdapter(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutCitySearchField.visibility = View.GONE
                binding.cityLocationPickerButtonFlightTickets.text = selectedItem
                binding.cityLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.cityTitleText.text = "Город"

                binding.airportTitleText.text = "Выберите аэропорт"
                binding.airportTitleText.visibility = View.VISIBLE
                binding.airportLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                binding.airportLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            }
        })

        binding.cityListView.adapter = adapterCity

        binding.editTextSearchCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val countryNameInButton: String = binding.countyLocationPickerButtonFlightTickets.text.toString()
                val currentCountryCities: List<String> = getCitiesByCountryName(countryNameInButton)
                filter(editable.toString(), currentCountryCities, adapterCity)
            }
        })

        binding.clearCitySearchFieldButton.setOnClickListener {
            binding.editTextSearchCity.text.clear()
            val countryNameInButton: String = binding.countyLocationPickerButtonFlightTickets.text.toString()
            val currentCountryCities: List<String> = cities_data[countryNameInButton]!!
            filter("", currentCountryCities, adapterCity)
        }

        binding.cityLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.airportTitleText.visibility = View.GONE
            binding.airportLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.layoutAirportSearchField.visibility = View.GONE

            binding.cityLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchCity.text.clear()
            binding.layoutCitySearchField.visibility = View.VISIBLE
            binding.cityTitleText.text = "Выберите город"


        }









        adapterAirport = ListAdapter(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutAirportSearchField.visibility = View.GONE
                binding.airportLocationPickerButtonFlightTickets.text = selectedItem
                binding.airportLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.airportTitleText.text = "Аэропорт"
            }
        })

        binding.airportListView.adapter = adapterAirport

        binding.editTextSearchAirport.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val cityNameInButton: String = binding.cityLocationPickerButtonFlightTickets.text.toString()
                val currentCityAirports: List<String> = getAirportsByCityName(cityNameInButton)
                filter(editable.toString(), currentCityAirports, adapterAirport)
            }
        })

        binding.clearAirportSearchFieldButton.setOnClickListener {
            binding.editTextSearchAirport.text.clear()
            val cityNameInButton: String = binding.cityLocationPickerButtonFlightTickets.text.toString()
            val currentCityAirports: List<String> = airports_data[cityNameInButton]!!
            filter("", currentCityAirports, adapterAirport)
        }

        binding.airportLocationPickerButtonFlightTickets.setOnClickListener() {
            binding.airportLocationPickerButtonFlightTickets.visibility = View.GONE
            binding.editTextSearchAirport.text.clear()
            binding.layoutAirportSearchField.visibility = View.VISIBLE
            binding.airportTitleText.text = "Выберите аэропорт"
        }

        }

    interface OnItemClickListener {
        fun onItemClicked(selectedItem: String)
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


    override fun getAirportsByCityName(city_name: String): List<String> {
        return airports_data[city_name]!!
    }

    override fun getCitiesByCountryName(country_name: String): List<String> {
        return cities_data[country_name]!!
    }
}


interface UpdateListInterface {
    fun updateList(filteredItems: MutableList<String>)
}


class ListAdapter(context: Context,
                         private val items: List<String>,
                         private val itemClickListener: FlightTicketsActivity.OnItemClickListener) : BaseAdapter(), UpdateListInterface {

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
            LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
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


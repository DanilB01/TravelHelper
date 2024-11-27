package ru.itmo.travelhelper.screens.flightTicketsScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import ru.itmo.domain.models.flightTicketListModels.AirportModel
import ru.itmo.domain.models.flightTicketListModels.CityModel
import ru.itmo.domain.models.flightTicketListModels.CountryModel
import ru.itmo.travelhelper.databinding.FragmentFlightLocationArrivalBinding
import ru.itmo.travelhelper.presenter.FlightTicketsPresenter
import ru.itmo.travelhelper.view.FlightTicketsView


class FlightArrivalLocationFragment : Fragment(), FlightTicketsView {
    lateinit var binding: FragmentFlightLocationArrivalBinding
    private val presenter: FlightTicketsPresenter by lazy { FlightTicketsPresenter(this) }


    private lateinit var adapterCountry: ListAdapterArrival
    private lateinit var adapterCity: ListAdapterArrival
    private lateinit var adapterAirport: ListAdapterArrival

    lateinit var countries_data: List<String>
    lateinit var cities_data: Map<String, List<String>>
    lateinit var airports_data: Map<String, List<String>>

    lateinit var mutListOfLocationDepartureData: MutableList<String>


    private val dataFlightModel: DataFlightModel by activityViewModels ()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightLocationArrivalBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightArrivalLocationFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setupView()


        dataFlightModel.locationDepartureDataMessage.observe(activity as LifecycleOwner, {
            mutListOfLocationDepartureData = it
        })


        dataFlightModel.locationArrivalDataMessage.observe(activity as LifecycleOwner, { locationDataList ->

            binding.countryArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
            binding.countryArrivalTitleText.visibility = View.VISIBLE
            binding.countryArrivalTitleText.text = "Страна"

            if (locationDataList[0] != "") {
                binding.countryArrivalLocationPickerButtonFlightTickets.text = locationDataList[0]


                binding.cityArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.cityArrivalTitleText.visibility = View.VISIBLE
                binding.cityArrivalTitleText.text = "Город"

                if (locationDataList[1] != ""
                    && !(mutListOfLocationDepartureData[1] == locationDataList[1] && mutListOfLocationDepartureData[0] == locationDataList[0])) {

                    binding.cityArrivalLocationPickerButtonFlightTickets.text = locationDataList[1]

                    binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                    binding.airportArrivalTitleText.visibility = View.VISIBLE
                    binding.airportArrivalTitleText.text = "Аэропорт"

                    if (locationDataList[2] != "") {
                        binding.airportArrivalLocationPickerButtonFlightTickets.text = locationDataList[2]
                    }
                    else {
                        binding.airportArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать аэропорт"
                        dataFlightModel.locationArrivalDataMessage.value?.set(2, "")
                    }

                }
                else {
                    binding.cityArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать город"
                    dataFlightModel.locationArrivalDataMessage.value?.set(1, "")
                    dataFlightModel.locationArrivalDataMessage.value?.set(2, "")
                }
            }
            else {
                binding.countryArrivalLocationPickerButtonFlightTickets.text = "Нажмите, чтобы выбрать страну"

                dataFlightModel.locationArrivalDataMessage.value?.set(0, "")
                dataFlightModel.locationArrivalDataMessage.value?.set(1, "")
                dataFlightModel.locationArrivalDataMessage.value?.set(2, "")
            }







        })

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

                dataFlightModel.locationArrivalDataMessage.value?.set(0, selectedItem)

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

            dataFlightModel.locationArrivalDataMessage.value?.set(0, "")
            dataFlightModel.locationArrivalDataMessage.value?.set(1, "")
            dataFlightModel.locationArrivalDataMessage.value?.set(2, "")

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

                dataFlightModel.locationArrivalDataMessage.value?.set(1, selectedItem)
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

            dataFlightModel.locationArrivalDataMessage.value?.set(1, "")
            dataFlightModel.locationArrivalDataMessage.value?.set(2, "")


        }









        adapterAirport = ListAdapterArrival(this, emptyList(), object : OnItemClickListener {
            override fun onItemClicked(selectedItem: String) {
                binding.layoutAirportArrivalSearchField.visibility = View.GONE
                binding.airportArrivalLocationPickerButtonFlightTickets.text = selectedItem
                binding.airportArrivalLocationPickerButtonFlightTickets.visibility = View.VISIBLE
                binding.airportArrivalTitleText.text = "Аэропорт"

                dataFlightModel.locationArrivalDataMessage.value?.set(2, selectedItem)
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

            dataFlightModel.locationArrivalDataMessage.value?.set(2, "")
        }

    }


    interface OnItemClickListener {
        fun onItemClicked(selectedItem: String)
    }


    private fun filter(text: String, list_data: List<String>, adapterName: UpdateListInterface, location_data_id: Int) {
        val filteredList = mutableListOf<String>()
        val checkingWithDepartureData: String = mutListOfLocationDepartureData[location_data_id]
        if (text.isNotEmpty()) {
            for (item in list_data) {

                if (item.contains(text, ignoreCase = true) &&
                    !(location_data_id == 1 && item == checkingWithDepartureData && dataFlightModel.locationArrivalDataMessage.value?.get(0) == mutListOfLocationDepartureData[0])) {
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




class ListAdapterArrival(
    context: FlightArrivalLocationFragment,
    private val items: List<String>,
    private val itemClickListener: FlightArrivalLocationFragment.OnItemClickListener
) : BaseAdapter(), UpdateListInterface {

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
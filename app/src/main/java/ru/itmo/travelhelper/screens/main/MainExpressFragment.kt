package ru.itmo.travelhelper.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentMainExpressBinding
import ru.itmo.travelhelper.screens.flight.FlightActivity
import ru.itmo.travelhelper.screens.flight.adapter.FlightLocationsListAdapter
import ru.itmo.travelhelper.screens.flight.adapter.FlightLocationsOnItemClickListener
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesOnItemClickListener

class MainExpressFragment : Fragment() {
    lateinit var binding: FragmentMainExpressBinding
    private lateinit var adapterInterestingPlaces: MainInterestingPlacesListAdapter
    private val dataList = listOf(
        listOf("Мак","около Набережной","Круглосуточно","5+"),
        listOf("Театр","около Дома","Круглосуточно","4+"),
        listOf("Музей","около Улицы","Возраст: 6+","5"),
        listOf("Кино","там!","Новые фильмы появились","5++"),
        listOf("Университет","ломо","Выживание","5+++"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainExpressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartPlanningTravel.setOnClickListener {
            val intentFlightActivity = Intent(context, FlightActivity::class.java)
            startActivity(intentFlightActivity)
        }



        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterInterestingPlaces = MainInterestingPlacesListAdapter(emptyList(), object : MainInterestingPlacesOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {

            }
        })

        binding.recyclerView.adapter = adapterInterestingPlaces
        adapterInterestingPlaces.updateList(dataList)


    }

}
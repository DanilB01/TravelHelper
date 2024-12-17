package ru.itmo.travelhelper.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.main.InterestingPlaceModel
import ru.itmo.travelhelper.databinding.FragmentMainExpressBinding
import ru.itmo.travelhelper.presenter.main.MainExpressPresenter
import ru.itmo.travelhelper.screens.flight.FlightActivity
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesOnItemClickListener
import ru.itmo.travelhelper.screens.totalinfo.TotalInfoActivity
import ru.itmo.travelhelper.view.main.MainExpressView

class MainExpressFragment : Fragment(), MainExpressView {
    private val presenter: MainExpressPresenter by lazy { MainExpressPresenter(this) }
    lateinit var binding: FragmentMainExpressBinding
    private lateinit var adapterInterestingPlaces: MainInterestingPlacesListAdapter
    private lateinit var interestingPlaceData: List<List<String>>

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
//            val intentFlightActivity = Intent(context, FlightActivity::class.java)
            val intentFlightActivity = Intent(context, TotalInfoActivity::class.java)
            startActivity(intentFlightActivity)
        }


        presenter.loadInterestingPlaceData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterInterestingPlaces = MainInterestingPlacesListAdapter(emptyList(), object : MainInterestingPlacesOnItemClickListener {
            override fun onItemClicked(position: Int) {

            }
        })

        binding.recyclerView.adapter = adapterInterestingPlaces
        adapterInterestingPlaces.updateList(this.interestingPlaceData)


    }

    override fun getInterestingPlaceData(interestingPlaceData: List<InterestingPlaceModel>) {
        this.interestingPlaceData = interestingPlaceData
            .map { inter_place_item -> listOf(inter_place_item.placeName, inter_place_item.placeLocation,
                inter_place_item.placeDescription, inter_place_item.placeRating, inter_place_item.pictureName) }
    }

}
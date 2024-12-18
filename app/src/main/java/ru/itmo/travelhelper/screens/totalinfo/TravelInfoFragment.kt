package ru.itmo.travelhelper.screens.totalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel
import ru.itmo.travelhelper.databinding.FragmentTravelInfoBinding
import ru.itmo.travelhelper.presenter.totalinfo.TravelInfoPresenter
import ru.itmo.travelhelper.screens.totalinfo.adapter.TotalInfoPlacesListAdapter
import ru.itmo.travelhelper.screens.totalinfo.adapter.TotalInfoPlacesOnItemClickListener
import ru.itmo.travelhelper.screens.totalinfo.model.EnumChangeActivities
import ru.itmo.travelhelper.view.totalinfo.TravelInfoView


class TravelInfoFragment : Fragment(), TravelInfoView {
    private lateinit var adapterTotalInfoPlaces: TotalInfoPlacesListAdapter
    private lateinit var totalInfoPlaceData: List<List<String>>
    lateinit var binding: FragmentTravelInfoBinding
    private val presenter: TravelInfoPresenter by lazy { TravelInfoPresenter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadInterestingPlaceData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterTotalInfoPlaces = TotalInfoPlacesListAdapter(emptyList(), object : TotalInfoPlacesOnItemClickListener {
            override fun onItemClicked(position: Int) {

            }
        })

        binding.recyclerView.adapter = adapterTotalInfoPlaces
        adapterTotalInfoPlaces.updateList(this.totalInfoPlaceData)



        binding.buttonEditTotalInfo.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to "CHANGING_INFO"))
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() = TravelInfoFragment()

    }

    override fun getTotalInfoPlaceData(totalInfoPlaceData: List<TotalInfoPlaceModel>) {
        this.totalInfoPlaceData = totalInfoPlaceData
            .map { info_place_item -> listOf(info_place_item.placeName, info_place_item.placeLocation,
                info_place_item.placeDescription, info_place_item.placeRating, info_place_item.pictureName) }
    }
}
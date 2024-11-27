package ru.itmo.travelhelper.screens.flightTicketsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import ru.itmo.travelhelper.databinding.FragmentFlightDateBinding


class FlightDateFragment : Fragment() {
    lateinit var binding: FragmentFlightDateBinding

    private val dataFlightModel: DataFlightModel by activityViewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightDateBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightDateFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataFlightModel.locationArrivalDataMessage.observe(activity as LifecycleOwner, { locationArrivalDataList ->
            if (locationArrivalDataList.all { it.isNotEmpty() }) {
                binding.locationArrivalDataText.text = locationArrivalDataList.joinToString(", ")
            }

        })

        dataFlightModel.locationDepartureDataMessage.observe(activity as LifecycleOwner, { locationDepartureDataList ->
            if (locationDepartureDataList.all { it.isNotEmpty() }) {
                binding.locationDepartureDataText.text = locationDepartureDataList.joinToString(", ")
            }

        })
    }
}
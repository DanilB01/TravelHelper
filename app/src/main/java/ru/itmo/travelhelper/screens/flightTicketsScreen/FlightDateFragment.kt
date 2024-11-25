package ru.itmo.travelhelper.screens.flightTicketsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentFlightDateBinding
import ru.itmo.travelhelper.databinding.FragmentFlightLocationBinding


class FlightDateFragment : Fragment() {
    lateinit var binding: FragmentFlightDateBinding

    private val dataFlightModel: DataFlightModel by activityViewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDateBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightDateFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataFlightModel.locationDataMessage.observe(activity as LifecycleOwner, { locationDataList ->
            if (locationDataList.all { it.isNotEmpty() }) {
                binding.locationDataText.text = locationDataList.joinToString(", ")
            }

        })
    }
}
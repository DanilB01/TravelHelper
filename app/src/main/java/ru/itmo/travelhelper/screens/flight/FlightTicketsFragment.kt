package ru.itmo.travelhelper.screens.flight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itmo.travelhelper.databinding.FragmentFlightTicketsBinding


class FlightTicketsFragment : Fragment() {
    lateinit var binding: FragmentFlightTicketsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightTicketsBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightTicketsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}
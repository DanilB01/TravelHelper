package ru.itmo.travelhelper.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentMainEmptyTravelsBinding
import ru.itmo.travelhelper.screens.flight.FlightActivity
import ru.itmo.travelhelper.view.main.MainEmptyTravelsView


class MainEmptyTravelsFragment : Fragment(), MainEmptyTravelsView {
    lateinit var binding: FragmentMainEmptyTravelsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainEmptyTravelsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonYesMainEmptyScreen.setOnClickListener {
            val intentFlightActivity = Intent(context, FlightActivity::class.java)
            startActivity(intentFlightActivity)
        }

        binding.buttonNoMainEmptyScreen.setOnClickListener {
            findNavController().navigate(R.id.action_emptyTravelsItem_to_expressItem)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainEmptyTravelsFragment()
    }
}
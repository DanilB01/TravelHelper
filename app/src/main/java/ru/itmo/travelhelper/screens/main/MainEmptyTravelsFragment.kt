package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentMainEmptyTravelsBinding
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

    companion object {
        @JvmStatic
        fun newInstance() = MainEmptyTravelsFragment()
    }
}
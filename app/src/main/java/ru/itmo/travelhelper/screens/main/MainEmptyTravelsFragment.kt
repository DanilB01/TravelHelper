package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itmo.travelhelper.databinding.FragmentMainEmptyTravelsBinding


class MainEmptyTravelsFragment : Fragment() {
    lateinit var binding: FragmentMainEmptyTravelsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
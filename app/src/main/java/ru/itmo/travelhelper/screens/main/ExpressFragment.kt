package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentExpressBinding

class ExpressFragment : Fragment() {
    lateinit var binding: FragmentExpressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpressBinding.inflate(inflater)
        return binding.root
    }

}
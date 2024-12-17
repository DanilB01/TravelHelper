package ru.itmo.travelhelper.screens.totalinfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentChangingInfoBinding
import ru.itmo.travelhelper.screens.totalinfo.model.TotalInfoFragments


class ChangingInfoFragment : Fragment() {
    lateinit var binding: FragmentChangingInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChangingInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.endEditingFragmentTotalInfo.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to TotalInfoFragments.TRAVEL_INFO.name))
        }

        binding.buttonChangeAirTickets.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to TotalInfoFragments.WARNING_INFO.name))
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChangingInfoFragment()
    }


}
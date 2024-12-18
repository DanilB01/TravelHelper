package ru.itmo.travelhelper.screens.totalinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.itmo.travelhelper.databinding.FragmentChangingInfoBinding
import ru.itmo.travelhelper.screens.totalinfo.model.EnumChangeActivities
import ru.itmo.travelhelper.screens.totalinfo.model.EnumTotalInfoFragments


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
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to EnumTotalInfoFragments.TRAVEL_INFO.name))
        }

        binding.buttonChangeAirTickets.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to EnumTotalInfoFragments.WARNING_INFO.name))
        }

        binding.buttonChangeHotel.setOnClickListener {
                parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChangingActivity", bundleOf("activityId" to EnumChangeActivities.HOTEL_ACT.name))
        }

        binding.buttonChangeAction.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChangingActivity", bundleOf("activityId" to EnumChangeActivities.ACTIVITIES_ACT.name))
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChangingInfoFragment()
    }


}
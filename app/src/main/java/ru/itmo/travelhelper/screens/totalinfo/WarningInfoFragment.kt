package ru.itmo.travelhelper.screens.totalinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.itmo.travelhelper.databinding.FragmentWarningInfoBinding
import ru.itmo.travelhelper.screens.totalinfo.model.TotalInfoFragments


class WarningInfoFragment : Fragment() {
    lateinit var binding: FragmentWarningInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWarningInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonNoFragmentTotalInfo.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestTotalInfoToActivityForChanging", bundleOf("fragmentId" to TotalInfoFragments.CHANGING_INFO.name))
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = WarningInfoFragment()
    }
}
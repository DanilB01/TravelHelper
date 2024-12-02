package ru.itmo.travelhelper.screens.flightScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itmo.travelhelper.databinding.FragmentFlightDateBinding
import ru.itmo.travelhelper.presenter.flightPresentors.FlightPresenterDate


class FlightDateFragment : Fragment() {
    private val presenter: FlightPresenterDate by lazy { FlightPresenterDate(this) }
    lateinit var binding: FragmentFlightDateBinding


    var localDepartureDataList: MutableList<String> = mutableListOf("","","")
    var localArrivalDataList: MutableList<String> = mutableListOf("","","")



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
        parentFragmentManager.setFragmentResultListener("requestFlightToDateFromActivity", this) { _, result ->
            result.getStringArrayList("DateDataListFromAct")?.let { presenter.updateFullSavedDateData(it.toMutableList()) }
            localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            localArrivalDataList = result.getStringArrayList("ArrivalDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            binding.locationArrivalDataText.text = localArrivalDataList.joinToString(", ")
            binding.locationDepartureDataText.text = localDepartureDataList.joinToString(", ")

        }

    }
}
package ru.itmo.travelhelper.screens.flightTicketsScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityFlightTicketsMainBinding

class FlightTicketsActivity() : AppCompatActivity() {


    private val binding by lazy { ActivityFlightTicketsMainBinding.inflate(layoutInflater) }
    private val dataFlightModel: DataFlightModel by viewModels ()
    lateinit var mutListOfLocationArrivalData: MutableList<String>
    lateinit var mutListOfLocationDepartureData: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataFlightModel.locationArrivalDataMessage.value = mutableListOf("","","")
        dataFlightModel.locationDepartureDataMessage.value = mutableListOf("","","")

        var currentFragmentNumber = 0
        openFragment(chooseFragment(currentFragmentNumber))

        dataFlightModel.locationArrivalDataMessage.observe(this, {
            mutListOfLocationArrivalData = it
        })

        dataFlightModel.locationDepartureDataMessage.observe(this, {
            mutListOfLocationDepartureData = it
        })

        binding.goToNextFragmentFlight.setOnClickListener {

            if (currentFragmentNumber == 0 && mutListOfLocationDepartureData.all { it.isNotEmpty() }) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))

            }

            else if (currentFragmentNumber == 1 && mutListOfLocationArrivalData.all { it.isNotEmpty() }) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))
                 }

            else if (currentFragmentNumber == 2) {
                // TODO go to 3 screen list of appropriate tickets
            }
        }

        binding.goToBackFragmentFlight.setOnClickListener {
            if (currentFragmentNumber > 0) {
                currentFragmentNumber--
                openFragment(chooseFragment(currentFragmentNumber))
                }
        }

    }

    private fun chooseFragment(idFragment: Int): Fragment {
        var fragmentChosen: Fragment = FlightArrivalLocationFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = FlightDepartureLocationFragment.newInstance()
            1 -> fragmentChosen = FlightArrivalLocationFragment.newInstance()
            2 -> fragmentChosen = FlightDateFragment.newInstance()
        }
        return fragmentChosen
    }

    private fun openFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolderForFragmentsFlightTickets, frag)
            .commit()
    }
}




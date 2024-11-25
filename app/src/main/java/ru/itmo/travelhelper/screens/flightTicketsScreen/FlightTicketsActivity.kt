package ru.itmo.travelhelper.screens.flightTicketsScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityFlightTicketsMainBinding

class FlightTicketsActivity() : AppCompatActivity() {


    private val binding by lazy { ActivityFlightTicketsMainBinding.inflate(layoutInflater) }
    private val dataFlightModel: DataFlightModel by viewModels ()
    lateinit var mutListOfLocationData: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dataFlightModel.locationDataMessage.value = mutableListOf("","","")
        var currentFragmentNumber: Int = 0
        openFragment(chooseFragment(currentFragmentNumber))

        dataFlightModel.locationDataMessage.observe(this, { it
            mutListOfLocationData = it
        })

        binding.goToNextFragmentFlight.setOnClickListener {

            if (currentFragmentNumber == 0 && mutListOfLocationData.all { it.isNotEmpty() }) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))

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
        var fragmentChosen: Fragment = FlightLocationFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = FlightLocationFragment.newInstance()
            1 -> fragmentChosen = FlightDateFragment.newInstance()
        }
        return fragmentChosen
    }

    private fun openFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolderForFragmentsFlightTickets, frag)
            .commit()
    }
}




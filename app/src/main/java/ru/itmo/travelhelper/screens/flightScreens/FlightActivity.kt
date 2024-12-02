package ru.itmo.travelhelper.screens.flightScreens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityFlightTicketsMainBinding
import ru.itmo.travelhelper.presenter.flightPresentors.FlightPresenter
import ru.itmo.travelhelper.view.flightViews.FlightActivityView

class FlightActivity() : AppCompatActivity(), FlightActivityView {


    private val binding by lazy { ActivityFlightTicketsMainBinding.inflate(layoutInflater) }
    private val presenter: FlightPresenter by lazy { FlightPresenter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromArrival", this) { _, result ->
            result.getStringArrayList("ArrivalListData")
                ?.let { presenter.updateGlobalSavedArrivalData(it.toMutableList()) }
        }


        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDepartureBool", this) { _, result ->
            presenter.updateIsDepartureFull(result.getBoolean("isDepartureFull"))
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromArrivalBool", this) { _, result ->
            presenter.updateIsArrivalFull(result.getBoolean("isArrivalFull"))
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDeparture", this) { _, result ->
            result.getStringArrayList("DepartureListData")
                ?.let { presenter.updateGlobalSavedDepartureData(it.toMutableList()) }
        }



        var currentFragmentNumber = 0
        openFragment(chooseFragment(currentFragmentNumber))


        binding.goToNextFragmentFlight.setOnClickListener {
            if (currentFragmentNumber == 0 && presenter.isDepartureFull) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDeparture", this) { _, result ->
                    result.getStringArrayList("DepartureListData")
                        ?.let { presenter.updateGlobalSavedDepartureData(it.toMutableList()) }


                    supportFragmentManager.setFragmentResult("requestFlightToArrivalFromActivity",
                        bundleOf(
                            "DepartureDataListFromAct" to presenter.giveDepartureData(),
                            "ArrivalDataListFromAct" to presenter.giveArrivalData()))
                }



            }

            else if (currentFragmentNumber == 1 && presenter.isArrivalFull) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromArrival", this) { _, result ->
                    result.getStringArrayList("ArrivalListData")
                        ?.let { presenter.updateGlobalSavedArrivalData(it.toMutableList()) }

                    supportFragmentManager.setFragmentResult("requestFlightToDateFromActivity",
                        bundleOf(
                            "DateDataListFromAct" to presenter.giveDateData(),
                            "DepartureDataListFromAct" to presenter.giveDepartureData(),
                            "ArrivalDataListFromAct" to presenter.giveArrivalData()))
                }
            }

            else if (currentFragmentNumber == 2) {
                // TODO go to 3 screen list of appropriate tickets
            }

        }


        binding.goToBackFragmentFlight.setOnClickListener {
            if (currentFragmentNumber == 1) {
                currentFragmentNumber--
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResult("requestFlightToDepartureFromActivity",
                    bundleOf(
                        "DepartureDataListFromAct" to presenter.giveDepartureData(),))
            }
            else if (currentFragmentNumber == 2) {
                currentFragmentNumber--
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResult("requestFlightToArrivalFromActivity",
                    bundleOf(
                        "ArrivalDataListFromAct" to presenter.giveArrivalData(),))
            }
        }

    }

    private fun chooseFragment(idFragment: Int): Fragment {
        var fragmentChosen: Fragment = FlightArrivalFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = FlightDepartureFragment.newInstance()
            1 -> fragmentChosen = FlightArrivalFragment.newInstance()
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




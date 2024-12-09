package ru.itmo.travelhelper.screens.flight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityFlightTicketsMainBinding
import ru.itmo.travelhelper.presenter.flight.FlightPresenter
import ru.itmo.travelhelper.view.flight.FlightActivityView

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

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDateBool", this) { _, result ->
            presenter.updateIsDateFull(result.getBoolean("isDateFull"))
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDeparture", this) { _, result ->
            result.getStringArrayList("DepartureListData")
                ?.let { presenter.updateGlobalSavedDepartureData(it.toMutableList()) }
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDate", this) { _, result ->
            result.getStringArrayList("DateListData")
                ?.let { presenter.updateGlobalSavedDateData(it.toMutableList()) }
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromTicketThere", this) { _, result ->
            presenter.updateGlobalSavedTicketThereData(result.getInt("TicketThereListData"))
        }

        supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromTicketReturn", this) { _, result ->
            presenter.updateGlobalSavedTicketReturnData(result.getInt("TicketReturnListData"))
        }



        var currentFragmentNumber = 0
        openFragment(chooseFragment(currentFragmentNumber))

        // 0 - Departure Screen
        // 1 - Arrival Screen
        // 2 - Date Screen
        // 3 - Tickets There Screen
        // 4 - Tickets Return Screen

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

            else if (currentFragmentNumber == 2 && presenter.isDateFull) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromDate", this) { _, result ->
                    result.getStringArrayList("DateListData")
                        ?.let { presenter.updateGlobalSavedDateData(it.toMutableList()) }

                    supportFragmentManager.setFragmentResult("requestFlightToTicketThereFromActivity",
                        bundleOf(
                            "DateDataListFromAct" to presenter.giveDateData(),
                            "DepartureDataListFromAct" to presenter.giveDepartureData(),
                            "ArrivalDataListFromAct" to presenter.giveArrivalData(),
                            "TicketThereDataListFromAct" to presenter.giveTicketThereData()))
                }

            }


            else if (currentFragmentNumber == 3 && presenter.getIsReturnBoxChecked()) {
                currentFragmentNumber++
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResultListener("requestFlightToActivityFromTicketThere", this) { _, result ->
                    presenter.updateGlobalSavedTicketThereData(result.getInt("TicketThereListData"))
                    supportFragmentManager.setFragmentResult(
                        "requestFlightToTicketReturnFromActivity",
                        bundleOf(
                            "DateDataListFromAct" to presenter.giveDateData(),
                            "DepartureDataListFromAct" to presenter.giveDepartureData(),
                            "ArrivalDataListFromAct" to presenter.giveArrivalData(),
                            "TicketReturnDataListFromAct" to presenter.giveTicketReturnData()
                        )
                    )

                }
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
                        "ArrivalDataListFromAct" to presenter.giveArrivalData(),
                        "DepartureDataListFromAct" to presenter.giveDepartureData(),))
            }
            else if (currentFragmentNumber == 3) {
                currentFragmentNumber--
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResult("requestFlightToDateFromActivity",
                    bundleOf(
                        "ArrivalDataListFromAct" to presenter.giveArrivalData(),
                        "DepartureDataListFromAct" to presenter.giveDepartureData(),
                        "DateDataListFromAct" to presenter.giveDateData(),))
            }
            else if (currentFragmentNumber == 4) {
                currentFragmentNumber--
                openFragment(chooseFragment(currentFragmentNumber))
                supportFragmentManager.setFragmentResult("requestFlightToTicketThereFromActivity",
                    bundleOf(
                        "ArrivalDataListFromAct" to presenter.giveArrivalData(),
                        "DepartureDataListFromAct" to presenter.giveDepartureData(),
                        "DateDataListFromAct" to presenter.giveDateData(),
                        "TicketThereDataListFromAct" to presenter.giveTicketThereData()))
            }

        }

    }

    private fun chooseFragment(idFragment: Int): Fragment {
        var fragmentChosen: Fragment = FlightArrivalFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = FlightDepartureFragment.newInstance()
            1 -> fragmentChosen = FlightArrivalFragment.newInstance()
            2 -> fragmentChosen = FlightDateFragment.newInstance()
            3 -> fragmentChosen = FlightTicketsThereFragment.newInstance()
            4 -> fragmentChosen = FlightTicketsReturnFragment.newInstance()
        }
        return fragmentChosen
    }

    private fun openFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolderForFragmentsFlightTickets, frag)
            .commit()
    }

}


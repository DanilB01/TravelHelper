package ru.itmo.travelhelper.screens.totalinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityTotalInfoBinding
import ru.itmo.travelhelper.screens.activities.ActivitiesActivity
import ru.itmo.travelhelper.screens.flight.FlightActivity
import ru.itmo.travelhelper.screens.hotels.HotelActivity
import ru.itmo.travelhelper.screens.totalinfo.model.EnumChangeActivities
import ru.itmo.travelhelper.screens.totalinfo.model.EnumTotalInfoFragments
import ru.itmo.travelhelper.view.totalinfo.TotalInfoView

class TotalInfoActivity : AppCompatActivity(), TotalInfoView {
    private val binding by lazy { ActivityTotalInfoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        openFragment(chooseFragment(EnumTotalInfoFragments.TRAVEL_INFO))

        supportFragmentManager.setFragmentResultListener("requestTotalInfoToActivityForChanging", this) { _, result ->
            when (result.getString("fragmentId")) {
                EnumTotalInfoFragments.TRAVEL_INFO.name -> openFragment(chooseFragment(EnumTotalInfoFragments.TRAVEL_INFO))
                EnumTotalInfoFragments.CHANGING_INFO.name -> openFragment(chooseFragment(EnumTotalInfoFragments.CHANGING_INFO))
                EnumTotalInfoFragments.WARNING_INFO.name -> openFragment(chooseFragment(EnumTotalInfoFragments.WARNING_INFO))
            }
        }

        supportFragmentManager.setFragmentResultListener("requestTotalInfoToActivityForChangingActivity", this) { _, result ->
            when (result.getString("activityId")) {
                EnumChangeActivities.TICKETS_ACT.name -> openActivity(EnumChangeActivities.TICKETS_ACT)
                EnumChangeActivities.HOTEL_ACT.name -> openActivity(EnumChangeActivities.HOTEL_ACT)
                EnumChangeActivities.ACTIVITIES_ACT.name -> openActivity(EnumChangeActivities.ACTIVITIES_ACT)
            }
        }



    }


    fun chooseFragment(idFragment: EnumTotalInfoFragments): Fragment {
        return when (idFragment) {
            EnumTotalInfoFragments.TRAVEL_INFO -> TravelInfoFragment.newInstance()
            EnumTotalInfoFragments.CHANGING_INFO -> ChangingInfoFragment.newInstance()
            EnumTotalInfoFragments.WARNING_INFO -> WarningInfoFragment.newInstance()
        }
    }

    fun openActivity(idFragment: EnumChangeActivities) {
        when (idFragment) {
            EnumChangeActivities.TICKETS_ACT -> startActivity(Intent(this, FlightActivity::class.java))
            EnumChangeActivities.HOTEL_ACT -> {
                val intent = Intent(this, HotelActivity::class.java)
                intent.putExtra("isEditableMode", true)
                startActivity(intent)}
            EnumChangeActivities.ACTIVITIES_ACT -> startActivity(Intent(this, ActivitiesActivity::class.java))
        }
    }

    private fun openFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolderForFragmentsTotalInfo, frag)
            .commit()
    }

}



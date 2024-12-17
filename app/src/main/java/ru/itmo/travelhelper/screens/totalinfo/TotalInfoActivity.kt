package ru.itmo.travelhelper.screens.totalinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityTotalInfoBinding
import ru.itmo.travelhelper.screens.totalinfo.model.TotalInfoFragments
import ru.itmo.travelhelper.view.totalinfo.TotalInfoView

class TotalInfoActivity : AppCompatActivity(), TotalInfoView {
    private val binding by lazy { ActivityTotalInfoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        openFragment(chooseFragment(TotalInfoFragments.TRAVEL_INFO))

        supportFragmentManager.setFragmentResultListener("requestTotalInfoToActivityForChanging", this) { _, result ->
            when (result.getString("fragmentId")) {
                TotalInfoFragments.TRAVEL_INFO.name -> openFragment(chooseFragment(TotalInfoFragments.TRAVEL_INFO))
                TotalInfoFragments.CHANGING_INFO.name -> openFragment(chooseFragment(TotalInfoFragments.CHANGING_INFO))
                TotalInfoFragments.WARNING_INFO.name -> openFragment(chooseFragment(TotalInfoFragments.WARNING_INFO))
            }

        }

    }


    fun chooseFragment(idFragment: TotalInfoFragments): Fragment {
        return when (idFragment) {
            TotalInfoFragments.TRAVEL_INFO -> TravelInfoFragment.newInstance()
            TotalInfoFragments.CHANGING_INFO -> ChangingInfoFragment.newInstance()
            TotalInfoFragments.WARNING_INFO -> WarningInfoFragment.newInstance()
        }
    }


    private fun openFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolderForFragmentsTotalInfo, frag)
            .commit()
    }
}
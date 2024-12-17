package ru.itmo.travelhelper.view.hotel

import androidx.fragment.app.Fragment

interface HotelView {
    fun openNextFragment()
    fun openPrevFragment()
    fun chooseFragment(fragmentId: Int) : Fragment
    fun openFragment(fragment : Fragment)



}
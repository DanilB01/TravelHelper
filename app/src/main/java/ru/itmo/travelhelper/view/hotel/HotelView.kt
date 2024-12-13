package ru.itmo.travelhelper.view.hotel

import androidx.fragment.app.Fragment
import ru.itmo.domain.models.hotelModels.Hotel

interface HotelView {
    fun openNextFragment()
    fun openPrevFragment()
    fun chooseFragment(fragmentId: Int) : Fragment
    fun openFragment(fragment : Fragment)



}
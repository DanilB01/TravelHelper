package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R

class HotelSelectionFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_selection, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HotelSelectionFragment()
    }
}
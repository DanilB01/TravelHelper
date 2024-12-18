package ru.itmo.travelhelper.screens.hotels

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentSkipHotelSelectionQuestionBinding
import ru.itmo.travelhelper.screens.activities.ActivitiesActivity
import ru.itmo.travelhelper.screens.totalinfo.TotalInfoActivity

/**
 * A simple [Fragment] subclass.
 * Use the [HotelSkipSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelSkipSelectionFragment : Fragment(R.layout.fragment_skip_hotel_selection_question) {
    private var _binding: FragmentSkipHotelSelectionQuestionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSkipHotelSelectionQuestionBinding.inflate(inflater, container, false)
        binding.btnAcceptSelection.setOnClickListener{
            (activity as HotelActivity).openNextFragment()
        }

        binding.btnSkipSelection.setOnClickListener {
            startActivity(Intent(context, ActivitiesActivity::class.java))
        }
        return binding.root
    }



    companion object {

        @JvmStatic
        fun newInstance() = HotelSkipSelectionFragment()
    }
}
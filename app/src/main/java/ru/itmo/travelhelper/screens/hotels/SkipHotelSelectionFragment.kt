package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentSkipHotelSelectionQuestionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SkipHotelSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkipHotelSelectionFragment : Fragment(R.layout.fragment_skip_hotel_selection_question) {
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
        return binding.root
    }



    companion object {

        @JvmStatic
        fun newInstance() = SkipHotelSelectionFragment()
    }
}
package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentDateSelectionBinding

class DateSelectionFragment : Fragment() {

    private var _binding: FragmentDateSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDateSelectionBinding.inflate(inflater, container, false)
        binding.editTextDateCheckIn.setOnClickListener{
            binding.constraintLayoutDatePickerCheckIn.visibility = View.VISIBLE

        }
        return binding.root
    }


    companion object {

        fun newInstance() =
            DateSelectionFragment()
    }

}
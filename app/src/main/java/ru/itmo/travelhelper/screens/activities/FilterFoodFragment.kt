package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentFilterFoodBinding
import ru.itmo.travelhelper.screens.activities.DetailsFragment.Companion.ARG_SELECTED_CATEGORIES

class FilterFoodFragment : Fragment() {

    // Создаем переменную для ViewBinding
    private var _binding: FragmentFilterFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Обработка кнопки "Назад"
        binding.backButton.setOnClickListener {
            val selectedCategories = arguments?.getStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES)
            val detailsFragment = DetailsFragment.newInstance(selectedCategories ?: emptyArray())
            (requireActivity() as ActivitiesActivity).replaceFragment(detailsFragment)
        }

        // Обработка кнопки "Далее"
        binding.nextButton.setOnClickListener {
            val selectedCategories = arguments?.getStringArray(ARG_SELECTED_CATEGORIES)
            val foodListFragment = FoodListFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES, selectedCategories)
                }
            }
            (requireActivity() as ActivitiesActivity).replaceFragment(foodListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
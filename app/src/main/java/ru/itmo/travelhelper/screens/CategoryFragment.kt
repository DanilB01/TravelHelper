package ru.itmo.travelhelper.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itmo.travelhelper.databinding.FragmentCategoryBinding
import ru.itmo.domain.repositories.CategoryRepository
import ru.itmo.domain.usecases.GetCategoriesUseCase
import ru.itmo.travelhelper.presenter.CategoryPresenter
import ru.itmo.travelhelper.view.CategoryView

class CategoryFragment : Fragment(), CategoryView {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: CategoryPresenter

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CategoryRepository()
        getCategoriesUseCase = GetCategoriesUseCase(repository)
        presenter = CategoryPresenter(this)

        binding.nextButton.setOnClickListener {
            val selectedCategories = mutableListOf<String>()
            if (binding.eventsCheckBox.isChecked) selectedCategories.add("events")
            if (binding.placesCheckBox.isChecked) selectedCategories.add("places")
            if (binding.foodCheckBox.isChecked) selectedCategories.add("food")

            presenter.onNextButtonClicked(selectedCategories)
        }
    }


    override fun navigateToDetails(selectedCategories: Array<String>) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(selectedCategories)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

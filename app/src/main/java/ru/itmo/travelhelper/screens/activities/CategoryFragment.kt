package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentCategoryBinding
import ru.itmo.domain.repositories.activities.CategoryRepositoryImpl
import ru.itmo.domain.usecases.activities.GetCategoriesUseCase
import ru.itmo.travelhelper.presenter.activities.CategoryPresenter
import ru.itmo.travelhelper.view.activities.CategoryView

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

        val repository = CategoryRepositoryImpl()
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
        val detailsFragment = DetailsFragment.newInstance(selectedCategories)
        (requireActivity() as ActivitiesActivity).replaceFragment(detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
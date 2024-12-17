package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentDetailsBinding
import ru.itmo.travelhelper.presenter.activities.DetailsPresenter
import ru.itmo.domain.usecases.activities.GetDetailsUseCase
import ru.itmo.travelhelper.view.activities.DetailsView

class DetailsFragment : Fragment(), DetailsView {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: DetailsPresenter
    private lateinit var getDetailsUseCase: GetDetailsUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetailsUseCase = GetDetailsUseCase()
        presenter = DetailsPresenter(this)

        val selectedCategories = arguments?.getStringArray(ARG_SELECTED_CATEGORIES) ?: emptyArray()
        presenter.onCategoriesLoaded(selectedCategories, getDetailsUseCase)

        binding.backButton.setOnClickListener {
            (requireActivity() as ActivitiesActivity).replaceFragment(CategoryFragment())
        }

        binding.eventsTextView.setOnClickListener {
            val selectedCategories = arguments?.getStringArray(ARG_SELECTED_CATEGORIES)
            val eventsListFragment = EventsListFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES, selectedCategories)
                }
            }
            (requireActivity() as ActivitiesActivity).replaceFragment(eventsListFragment)
        }
        binding.placesTextView.setOnClickListener {
            val selectedCategories = arguments?.getStringArray(ARG_SELECTED_CATEGORIES)
            val placesListFragment = PlacesListFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES, selectedCategories)
                }
            }
            (requireActivity() as ActivitiesActivity).replaceFragment(placesListFragment)
        }
        binding.foodTextView.setOnClickListener {
            val selectedCategories = arguments?.getStringArray(ARG_SELECTED_CATEGORIES)
            val filterFoodFragment = FilterFoodFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES, selectedCategories)
                }
            }
            (requireActivity() as ActivitiesActivity).replaceFragment(filterFoodFragment)
        }
    }

    override fun showEventsText(text: CharSequence) {
        binding.eventsTextView.visibility = View.VISIBLE
        binding.eventsTextView.text = text
    }

    override fun showPlacesText(text: CharSequence) {
        binding.placesTextView.visibility = View.VISIBLE
        binding.placesTextView.text = text
    }

    override fun showFoodText(text: CharSequence) {
        binding.foodTextView.visibility = View.VISIBLE
        binding.foodTextView.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SELECTED_CATEGORIES = "selectedCategories"

        fun newInstance(selectedCategories: Array<String>): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putStringArray(ARG_SELECTED_CATEGORIES, selectedCategories)
            fragment.arguments = args
            return fragment
        }
    }
}

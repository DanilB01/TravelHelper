package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.activities.Event
import ru.itmo.domain.repositories.activities.PlacesRepositoryImpl
import ru.itmo.travelhelper.databinding.FragmentPlacesListBinding
import ru.itmo.travelhelper.presenter.activities.EventsListPresenter
import ru.itmo.travelhelper.screens.activities.adapter.EventsAdapter
import ru.itmo.travelhelper.view.activities.EventsListView

class PlacesListFragment : Fragment(), EventsListView {

    private var _binding: FragmentPlacesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: EventsListPresenter
    private lateinit var adapter: EventsAdapter
    private var selectedRating: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlacesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = EventsListPresenter(this, PlacesRepositoryImpl()) // или передайте зависимость через DI
        setupRecyclerView()
        setupRatingFilter()
        presenter.loadEvents()
    }

    private fun setupRecyclerView() {
        adapter = EventsAdapter { event ->
            onEventSelected(event)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupRatingFilter() {
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            selectedRating = if (rating > 0) rating.toDouble() else null
            presenter.filterEvents(selectedRating)
        }
    }

    private fun onEventSelected(event: Event) {
        val selectedCategories = arguments?.getStringArray(DetailsFragment.ARG_SELECTED_CATEGORIES)
        val detailsFragment = DetailsFragment.newInstance(selectedCategories ?: emptyArray())
        (requireActivity() as ActivitiesActivity).replaceFragment(detailsFragment)
    }

    override fun showEvents(events: List<Event>) {
        adapter.submitList(events)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

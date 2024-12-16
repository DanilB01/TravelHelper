package ru.itmo.travelhelper.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itmo.travelhelper.databinding.FragmentDetailsBinding
import ru.itmo.travelhelper.presenter.DetailsPresenter
import ru.itmo.domain.usecases.GetDetailsUseCase
import ru.itmo.travelhelper.view.DetailsView

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

        // Инициализируем UseCase и Presenter
        getDetailsUseCase = GetDetailsUseCase()
        presenter = DetailsPresenter(this)

        // Получаем выбранные категории из аргументов
        val selectedCategories = arguments?.getStringArray("selectedCategories") ?: emptyArray()

        // Передаем их в Presenter
        presenter.onCategoriesLoaded(selectedCategories, getDetailsUseCase)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
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
}

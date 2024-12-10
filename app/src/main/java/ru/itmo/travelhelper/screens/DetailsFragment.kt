package ru.itmo.travelhelper.screens

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.itmo.travelhelper.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventsTextView: TextView = view.findViewById(R.id.eventsTextView)
        val placesTextView: TextView = view.findViewById(R.id.placesTextView)
        val foodTextView: TextView = view.findViewById(R.id.foodTextView)

        val eventsText = "Мероприятия\nРазличные события/активности, например, концерты, выставки, фестивали"
        val placesText = "Места\nЗнакомые локации, исторические объекты, достопримечательности, природные зоны"
        val foodText = "Еда\nКафе, рестораны и другие места, где можно насладиться разнообразными блюдами"

        val eventsLargeText = "Мероприятия"
        val placesLargeText = "Места"
        val foodLargeText = "Еда"

        val eventsSpannableString = SpannableString(eventsText)
        val placesSpannableString = SpannableString(placesText)
        val foodSpannableString = SpannableString(foodText)

        // Мероприятия
        eventsSpannableString.setSpan(
            RelativeSizeSpan(1.5f), // Размер 150% от базового для "Мероприятия"
            0,
            eventsLargeText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        eventsSpannableString.setSpan(
            RelativeSizeSpan(1f), // Размер 80% от базового для "Различные события..."
            eventsLargeText.length + 1, // Учитываем символ новой строки
            eventsText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Места
        placesSpannableString.setSpan(
            RelativeSizeSpan(1.5f), // Размер 150% от базового для "Мероприятия"
            0,
            placesLargeText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        placesSpannableString.setSpan(
            RelativeSizeSpan(1f), // Размер 80% от базового для "Различные события..."
            placesLargeText.length + 1, // Учитываем символ новой строки
            placesText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Еда
        foodSpannableString.setSpan(
            RelativeSizeSpan(1.5f), // Размер 150% от базового для "Мероприятия"
            0,
            foodLargeText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        foodSpannableString.setSpan(
            RelativeSizeSpan(1f), // Размер 80% от базового для "Различные события..."
            foodLargeText.length + 1, // Учитываем символ новой строки
            foodText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        eventsTextView.text = eventsSpannableString
        placesTextView.text = placesSpannableString
        foodTextView.text = foodSpannableString
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
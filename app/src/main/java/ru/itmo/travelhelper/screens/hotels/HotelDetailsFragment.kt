package ru.itmo.travelhelper.screens.hotels

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import ru.itmo.domain.models.hotelModels.HotelModel
import ru.itmo.travelhelper.databinding.FragmentHotelDetailsBinding


class HotelDetailsFragment(val hotelModel: HotelModel) : Fragment() {


    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        with(binding)
        {
            textViewHotelName.text = hotelModel.getHotelName()
            textViewHotelDescription.text = hotelModel.getDescription()
        }

        setupLink("http://www.google.com", "перейти", binding.textViewOfficialSiteLink)
        return binding.root
    }

    private fun setupLink(linkText: String, textShouldBeLinked: String, textView: TextView) {

        val link = Link(textShouldBeLinked)

            .setTextColor(Color.BLUE)
            .setTextColorOfHighlightedLink(Color.CYAN)
            .setHighlightAlpha(.4f)
            .setUnderlined(true)
            .setBold(false)
            .setOnClickListener {

                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(linkText)
                )
                startActivity(urlIntent)
            }
        textView.applyLinks(link)
    }

    companion object {

        @JvmStatic
        fun newInstance(hotelModel: HotelModel) =
            HotelDetailsFragment(hotelModel)
    }
}
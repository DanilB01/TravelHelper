package ru.itmo.travelhelper.screens.hotels

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.travelhelper.databinding.FragmentHotelSelectionBinding
import ru.itmo.travelhelper.presenter.hotels.HotelSelectionPresenter
import ru.itmo.travelhelper.view.hotel.HotelSelectionView

class HotelSelectionFragment : Fragment(), HotelSelectionView {

    private val presenter: HotelSelectionPresenter by lazy { HotelSelectionPresenter(this) }
    private var _binding: FragmentHotelSelectionBinding? = null
    private val binding get() = _binding!!
    private val adapter = HotelsListAdapter(::onHotelCardClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelSelectionBinding.inflate(inflater, container, false)
        loadHotels()
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                showCurrentValueSnackbar(progress)
                presenter.filterHotelsByPrice(progress)
                loadHotels()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })
        return binding.root

    }

    private fun showCurrentValueSnackbar(progress: Int) {
        val snackbar = Snackbar.make(binding.root, formatNumber(progress.toString()), Snackbar.LENGTH_SHORT)
        val params = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.setMargins(330+getOffsetForSnackBar(progress), 100, 0, 0)
        snackbar.view.apply {
            backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
            layoutParams = params
            elevation = 0F
        }
        snackbar.setTextColor(ColorStateList.valueOf(Color.BLACK))
        snackbar.show()
    }

    private fun getOffsetForSnackBar(progress: Int): Int {
        val coeff = 430
        val maxVal = binding.textViewMaxPrice.text.toString().dropLast(1).replace(" ","").toDouble()
        val minVal = binding.textViewMinPrice.text.toString().dropLast(1).replace(" ","").toDouble()
        val pers: Double = ((progress - minVal) / (maxVal - minVal))
        return (pers * coeff).toInt()
    }

    private fun formatNumber(numberStr: String): String {
        return numberStr.reversed().chunked(3).joinToString(" ").reversed()
    }

    fun onHotelCardClicked(hotel: Hotel) {
        val bundle = Bundle()
        bundle.putSerializable("SelectedHotelModel",hotel)


        parentFragmentManager.setFragmentResult(
            "requestFromHotelSelectionFragmentToActivity", bundle
        )

    }

    override fun loadHotels() {
        binding.apply {

            rcViewHotelCards.adapter = adapter
            adapter.setNewList(presenter.getVisibleHotels())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HotelSelectionFragment()
    }
}


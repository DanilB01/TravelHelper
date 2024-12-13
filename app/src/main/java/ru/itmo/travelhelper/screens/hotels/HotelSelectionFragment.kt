package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.domain.models.hotelModels.HotelModel
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
    ): View? {
        _binding = FragmentHotelSelectionBinding.inflate(inflater, container, false)
        loadHotels()
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
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

    fun onHotelCardClicked(hotelModel: HotelModel) {
        val bundle = Bundle()
        bundle.putSerializable("SelectedHotelModel",hotelModel)


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


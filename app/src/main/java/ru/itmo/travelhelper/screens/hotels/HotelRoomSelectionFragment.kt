package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.models.hotel.Room

import ru.itmo.travelhelper.databinding.FragmentRoomSelectionBinding

import ru.itmo.travelhelper.presenter.hotels.RoomSelectionPresenter
import ru.itmo.travelhelper.view.hotel.RoomSelectionView

class HotelRoomSelectionFragment(val selectedHotel: Hotel) : Fragment(),
    RoomSelectionView {

    private val presenter: RoomSelectionPresenter by lazy { RoomSelectionPresenter(this) }
    private var _binding: FragmentRoomSelectionBinding? = null
    private val binding get() = _binding!!
    private val adapter = RoomsListAdapter(::onRoomCardClicked)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomSelectionBinding.inflate(inflater, container, false)
        loadRooms()
        binding.textViewHotelName.text = selectedHotel.getHotelName()
        return binding.root

    }

    fun onRoomCardClicked(room: Room) {
        val bundle = Bundle()
        bundle.putSerializable("SelectedRoomModel", room)
        parentFragmentManager.setFragmentResult(
            "requestFromRoomSelectionFragmentToActivity", bundle
        )

    }


    override fun loadRooms() {
        binding.apply {

            rcViewRoomCards.adapter = adapter
            adapter.setNewList(presenter.getRooms())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(hotel: Hotel) =
            HotelRoomSelectionFragment(hotel)

    }
}
package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

import ru.itmo.travelhelper.databinding.FragmentRoomSelectionBinding

import ru.itmo.travelhelper.presenter.hotels.RoomSelectionPresenter
import ru.itmo.travelhelper.view.hotel.RoomSelectionView

class HotelRoomSelectionFragment(val hotelName : String) : Fragment(), RoomSelectionView {

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
        binding.textViewHotelName.text=hotelName
        return binding.root

    }

    fun onRoomCardClicked(roomData: Room) {

        parentFragmentManager.setFragmentResult(
            "requestFromRoomSelectionFragmentToActivity", bundleOf(
                "roomName" to roomData.getRoomName(),



            )
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
        fun newInstance(hotelName: String) =
            HotelRoomSelectionFragment(hotelName)

    }
}
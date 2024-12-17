package ru.itmo.travelhelper.presenter.hotels


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.HotelRepositoryImpl
import ru.itmo.travelhelper.R
import ru.itmo.domain.models.hotel.Room
import ru.itmo.domain.usecases.hotel.GetRoomsUseCase

import ru.itmo.travelhelper.view.hotel.RoomSelectionView


class RoomSelectionPresenter(private val view: RoomSelectionView) {

    private val getRoomsUseCase = GetRoomsUseCase(HotelRepositoryImpl())
    private val roomsData =
        arrayListOf(
            Room(
                R.drawable.init_picture_1,
                "Standart",
                40,
                arrayListOf(R.drawable.animal_icon)
            ),
            Room(
                R.drawable.init_picture_2,
                "Standart + ",
                40,
                arrayListOf(R.drawable.shower_icon)
            ),
            Room(
                R.drawable.init_picture_1,
                "Comfort",
                40,
                arrayListOf(R.drawable.animal_icon, R.drawable.shower_icon)
            ),

            )

    init {
        setupView()
    }

    private fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadRooms()
        }
    }

    private suspend fun loadRooms() {
        val rooms = getRoomsUseCase.execute()
    }

    fun getRooms(): ArrayList<Room> {
        return roomsData
    }


}
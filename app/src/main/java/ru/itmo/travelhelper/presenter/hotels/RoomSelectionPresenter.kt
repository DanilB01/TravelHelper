package ru.itmo.travelhelper.presenter.hotels


import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.screens.hotels.Hotel
import ru.itmo.travelhelper.screens.hotels.Room

import ru.itmo.travelhelper.view.hotel.RoomSelectionView


class RoomSelectionPresenter(private val view: RoomSelectionView) {

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


    fun getRooms(): ArrayList<Room> {
        return roomsData
    }


}
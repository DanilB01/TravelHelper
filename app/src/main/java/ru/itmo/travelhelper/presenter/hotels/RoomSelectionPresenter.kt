package ru.itmo.travelhelper.presenter.hotels


import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.screens.hotels.RoomModel

import ru.itmo.travelhelper.view.hotel.RoomSelectionView


class RoomSelectionPresenter(private val view: RoomSelectionView) {

    private val roomsData =
        arrayListOf(
            RoomModel(
                R.drawable.init_picture_1,
                "Standart",
                40,
                arrayListOf(R.drawable.animal_icon)
            ),
            RoomModel(
                R.drawable.init_picture_2,
                "Standart + ",
                40,
                arrayListOf(R.drawable.shower_icon)
            ),
            RoomModel(
                R.drawable.init_picture_1,
                "Comfort",
                40,
                arrayListOf(R.drawable.animal_icon, R.drawable.shower_icon)
            ),

            )


    fun getRooms(): ArrayList<RoomModel> {
        return roomsData
    }


}
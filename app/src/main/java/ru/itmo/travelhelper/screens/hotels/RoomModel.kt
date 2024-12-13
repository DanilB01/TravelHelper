package ru.itmo.travelhelper.screens.hotels

import java.io.Serializable


data class RoomModel(
    private val imageId: Int,
    private val roomName: String,
    private val square: Int,
    private val iconsId: ArrayList<Int>,


    ) : Serializable {
    fun getImageId(): Int {
        return imageId
    }

    fun getRoomName(): String {
        return roomName
    }

    fun getSquare(): Int {
        return square
    }

    fun getIconsId(): ArrayList<Int> {
        return iconsId
    }


}


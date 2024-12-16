package ru.itmo.domain.models.hotel

import java.io.Serializable


data class Room(
    private val imageId: Int = 0,
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


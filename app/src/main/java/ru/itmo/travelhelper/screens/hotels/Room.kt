package ru.itmo.travelhelper.screens.hotels


data class Room(
    private val imageId: Int,
    private val roomName: String,
    private val square: Int,
    private val iconsId: ArrayList<Int>,


    ) {
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


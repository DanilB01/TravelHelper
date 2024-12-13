package ru.itmo.domain.models.hotelModels

import java.io.Serializable


data class HotelModel(
    private val imageId: Int,
    private val hotelName: String,
    private val checkInTime: String,
    private val minPrice: Int,
    private val description: String = "",
    private val websiteURL: String = "https://www.google.ru/"


) : Serializable {
    fun getImageId(): Int {
        return imageId
    }

    fun getHotelName(): String {
        return hotelName
    }

    fun getCheckInTime(): String {
        return checkInTime
    }

    fun getMinPrice(): Int {
        return minPrice
    }


    fun getMinPriceString(): String {
        val minPriceArr = ArrayList<String>()
        var minPirceCopy = minPrice
        while (minPirceCopy > 0) {
            minPriceArr.add(0, (minPirceCopy % 1000).toString())
            minPirceCopy /= 1000
        }


        return minPriceArr.joinToString(" ")

    }

    fun getDescription(): String {
        return description
    }

    fun getWebsiteURL(): String {
        return websiteURL
    }
}


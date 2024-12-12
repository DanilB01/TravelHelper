package ru.itmo.travelhelper.presenter.hotels


import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.screens.hotels.Hotel
import ru.itmo.travelhelper.view.hotel.HotelSelectionView
import ru.itmo.travelhelper.view.hotel.HotelView
import kotlin.math.max

class HotelSelectionPresenter(private val view: HotelSelectionView) {

    private val hotelsData =
        arrayListOf(
            Hotel(R.drawable.init_picture_1, "Hotel name 1", "11:35", 8200),
            Hotel(R.drawable.init_picture_2, "Hotel name 2", "9:05", 11200),
            Hotel(R.drawable.init_picture_3, "Hotel name 3", "8:23", 13100),
            Hotel(R.drawable.init_picture_4, "Hotel name 4", "17:05", 14200),
        )
    private val visibleHotelsData =
        arrayListOf(
            Hotel(R.drawable.init_picture_1, "Hotel name 1", "11:35", 8200),
            Hotel(R.drawable.init_picture_2, "Hotel name 2", "9:05", 11200),
            Hotel(R.drawable.init_picture_3, "Hotel name 3", "8:23", 13100),
            Hotel(R.drawable.init_picture_4, "Hotel name 4", "17:05", 14200),
        )

    fun getHotels(): ArrayList<Hotel> {
        return hotelsData
    }

    fun getVisibleHotels(): ArrayList<Hotel> {
        return visibleHotelsData
    }

    fun filterHotelsByPrice(maxPrice: Int)
    {
        visibleHotelsData.clear()
        hotelsData.forEach()
        {
            if (it.getMinPrice() <= maxPrice)
            {
                visibleHotelsData.add(it)
            }
        }
    }

}
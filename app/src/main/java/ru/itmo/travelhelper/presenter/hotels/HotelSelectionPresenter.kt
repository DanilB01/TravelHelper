package ru.itmo.travelhelper.presenter.hotels


import ru.itmo.travelhelper.R
import ru.itmo.domain.models.hotelModels.HotelModel
import ru.itmo.travelhelper.view.hotel.HotelSelectionView

class HotelSelectionPresenter(private val view: HotelSelectionView) {

    private val hotelsData =
        arrayListOf(
            HotelModel(R.drawable.init_picture_1, "HotelModel name 1", "11:35", 8200),
            HotelModel(R.drawable.init_picture_2, "HotelModel name 2", "9:05", 11200),
            HotelModel(R.drawable.init_picture_3, "HotelModel name 3", "8:23", 13100),
            HotelModel(R.drawable.init_picture_4, "HotelModel name 4", "17:05", 14200),
        )
    private val visibleHotelsData =
        arrayListOf(
            HotelModel(R.drawable.init_picture_1, "HotelModel name 1", "11:35", 8200),
            HotelModel(R.drawable.init_picture_2, "HotelModel name 2", "9:05", 11200),
            HotelModel(R.drawable.init_picture_3, "HotelModel name 3", "8:23", 13100),
            HotelModel(R.drawable.init_picture_4, "HotelModel name 4", "17:05", 14200),
        )

    fun getHotels(): ArrayList<HotelModel> {
        return hotelsData
    }

    fun getVisibleHotels(): ArrayList<HotelModel> {
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
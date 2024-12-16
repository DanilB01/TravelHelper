package ru.itmo.travelhelper.presenter.hotels


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.HotelRepositoryImpl
import ru.itmo.travelhelper.R
import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.usecases.hotel.GetHotelsUseCase
import ru.itmo.travelhelper.view.hotel.HotelSelectionView

class HotelSelectionPresenter(private val view: HotelSelectionView) {

    private val getHotelsUseCase = GetHotelsUseCase(HotelRepositoryImpl())
    private val hotelsData =
        arrayListOf(
            Hotel(R.drawable.init_picture_1, "HotelModel name 1", "11:35", 8200),
            Hotel(R.drawable.init_picture_2, "HotelModel name 2", "9:05", 21200),
            Hotel(R.drawable.init_picture_3, "HotelModel name 3", "8:23", 33100),
            Hotel(R.drawable.init_picture_4, "HotelModel name 4", "17:05", 44200),
        )
    private val visibleHotelsData = arrayListOf<Hotel>()

    init {
        setupView()
        visibleHotelsData.addAll(hotelsData)
    }

    private fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadHotels()
        }
    }

    private suspend fun loadHotels() {
        val hotels = getHotelsUseCase.execute()
    }


    fun getHotels(): ArrayList<Hotel> {
        return hotelsData
    }

    fun getVisibleHotels(): ArrayList<Hotel> {
        return visibleHotelsData
    }

    fun filterHotelsByPrice(maxPrice: Int) {
        visibleHotelsData.clear()
        hotelsData.forEach() {
            if (it.getMinPrice() <= maxPrice) {
                visibleHotelsData.add(it)
            }
        }
    }

}
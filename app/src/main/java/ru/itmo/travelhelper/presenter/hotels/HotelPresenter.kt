package ru.itmo.travelhelper.presenter.hotels


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.HotelRepositoryImpl
import ru.itmo.domain.models.hotelModels.HotelModel
import ru.itmo.domain.usecases.hotel.GetHotelsUseCase
import ru.itmo.travelhelper.screens.hotels.RoomModel
import ru.itmo.travelhelper.view.hotel.HotelView

class HotelPresenter(private val view: HotelView) {
    private val getHotelsUseCase = GetHotelsUseCase(HotelRepositoryImpl())
    private var checkInDate: String = ""
    private var checkOutDate: String = ""
    private var visitorsCount: Int = 0
    private lateinit var hotelModel: HotelModel
    private lateinit var roomModel: RoomModel
    fun setCheckInDate(selectedCheckInDate: String) {
        checkInDate = selectedCheckInDate
    }

    fun setCheckOutDate(selectedCheckOutDate: String) {
        checkOutDate = selectedCheckOutDate
    }

    fun setVisitorsCount(visitorsCount: Int) {
        this.visitorsCount = visitorsCount
    }


    fun setSelectedHotelModel(model: HotelModel) {
        hotelModel = model
    }

    fun setSelectedRoomModel(selectedRoomModel: RoomModel) {
        roomModel = selectedRoomModel
    }

    fun getCheckInDate(): String {
        return checkInDate
    }

    fun getCheckOutDate(): String {
        return checkOutDate
    }

    fun getSelectedHotelModel(): HotelModel {
        return hotelModel
    }

    fun getSelectedRoomModel(): RoomModel {
        return roomModel
    }

    fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadHotels()
        }
    }

    private suspend fun loadHotels() {

        // Логика для загрузки данных
        // После загрузки данных
        val hotels = getHotelsUseCase.execute()

    }
}
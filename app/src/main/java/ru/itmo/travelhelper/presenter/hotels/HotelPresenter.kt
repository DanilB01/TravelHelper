package ru.itmo.travelhelper.presenter.hotels

import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.models.hotel.Room
import ru.itmo.travelhelper.view.hotel.HotelView

class HotelPresenter(private val view: HotelView) {
    private var checkInDate: String = ""
    private var checkOutDate: String = ""
    private var visitorsCount: Int = 0
    private lateinit var hotel: Hotel
    private lateinit var room: Room

    fun setCheckInDate(selectedCheckInDate: String) {
        checkInDate = selectedCheckInDate
    }

    fun setCheckOutDate(selectedCheckOutDate: String) {
        checkOutDate = selectedCheckOutDate
    }

    fun setVisitorsCount(visitorsCount: Int) {
        this.visitorsCount = visitorsCount
    }


    fun setSelectedHotelModel(model: Hotel) {
        hotel = model
    }

    fun setSelectedRoomModel(selectedRoom: Room) {
        room = selectedRoom
    }

    fun getCheckInDate(): String {
        return checkInDate
    }

    fun getCheckOutDate(): String {
        return checkOutDate
    }

    fun getSelectedHotelModel(): Hotel {
        return hotel
    }

    fun getSelectedRoomModel(): Room {
        return room
    }
}
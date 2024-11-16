package ru.itmo.travelhelper.view

import ru.itmo.domain.models.Hotel

interface HotelView {
    fun showLoading()
    fun hideLoading()
    fun showHotels(hotels: List<Hotel>)
    fun showError(message: String)
}
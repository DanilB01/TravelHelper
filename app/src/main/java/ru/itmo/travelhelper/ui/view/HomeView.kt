package ru.itmo.travelhelper.ui.view

import ru.itmo.travelhelper.domain.models.Hotel

interface HomeView {
    fun showLoading()
    fun hideLoading()
    fun showHotels(hotels: List<Hotel>)
    fun showError(message: String)
}
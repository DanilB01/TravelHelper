package ru.itmo.travelhelper.presenter

import usecases.GetHotelsUseCase
import ru.itmo.travelhelper.view.HomeView

class HomePresenter(private val view: HomeView, private val getHotelsUseCase: usecases.GetHotelsUseCase) {
    suspend fun loadHotels() {
        view.showLoading()
        // Логика для загрузки данных
        // После загрузки данных
        val hotels = getHotelsUseCase.execute()
        view.hideLoading()
        view.showHotels(hotels) // Пример данных
    }
}
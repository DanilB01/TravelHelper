package ru.itmo.travelhelper.ui.presenter

import ru.itmo.travelhelper.domain.usecases.GetHotelsUseCase
import ru.itmo.travelhelper.ui.view.HomeView

class HomePresenter(private val view: HomeView, private val getHotelsUseCase: GetHotelsUseCase) {
    suspend fun loadHotels() {
        view.showLoading()
        // Логика для загрузки данных
        // После загрузки данных
        val hotels = getHotelsUseCase.execute()
        view.hideLoading()
        view.showHotels(hotels) // Пример данных
    }
}
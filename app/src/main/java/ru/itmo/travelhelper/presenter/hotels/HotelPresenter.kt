package ru.itmo.travelhelper.presenter.hotels


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.itmo.data.repositories.HotelRepositoryImpl
import ru.itmo.domain.usecases.GetHotelsUseCase
import ru.itmo.travelhelper.view.HotelView

class HotelPresenter(private val view: HotelView) {
    private val getHotelsUseCase = GetHotelsUseCase(HotelRepositoryImpl())

    fun setupView() {
        GlobalScope.launch(Dispatchers.Main) {
            loadHotels()
        }
    }

    private suspend fun loadHotels() {
        view.showLoading()
        // Логика для загрузки данных
        // После загрузки данных
        val hotels = getHotelsUseCase.execute()
        view.hideLoading()
        view.showHotels(hotels) // Пример данных
    }
}
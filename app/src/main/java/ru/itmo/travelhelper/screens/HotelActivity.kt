package ru.itmo.travelhelper.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.domain.models.hotelModels.Hotel
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.presenter.HotelPresenter
import ru.itmo.travelhelper.view.HotelView

class HotelActivity : AppCompatActivity(), HotelView {
    private val presenter: HotelPresenter by lazy { HotelPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        presenter.setupView()
    }

    override fun showLoading() {
        // Показать индикатор загрузки
    }

    override fun hideLoading() {
        // Скрыть индикатор загрузки
    }

    override fun showHotels(hotels: List<Hotel>) {
        // Отобразить список отелей
    }

    override fun showError(message: String) {
        // Показать ошибку
    }
}

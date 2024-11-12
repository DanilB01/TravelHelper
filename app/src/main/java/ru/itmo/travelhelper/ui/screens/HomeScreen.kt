package ru.itmo.travelhelper.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.domain.models.Hotel
import ru.itmo.travelhelper.ui.presenter.HomePresenter
import ru.itmo.travelhelper.ui.view.HomeView

class HomeScreen : AppCompatActivity(), HomeView {
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        presenter.loadHotels()
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

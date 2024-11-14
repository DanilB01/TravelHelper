//package ru.itmo.travelhelper.screens
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import ru.itmo.travelhelper.R
//import models.Hotel
//import ru.itmo.travelhelper.presenter.HomePresenter
//import ru.itmo.travelhelper.view.HomeView
//
//class HomeScreen : AppCompatActivity(), HomeView {
//    private lateinit var presenter: HomePresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home_screen)
//        presenter.loadHotels()
//    }
//
//    override fun showLoading() {
//        // Показать индикатор загрузки
//    }
//
//    override fun hideLoading() {
//        // Скрыть индикатор загрузки
//    }
//
//    override fun showHotels(hotels: List<models.Hotel>) {
//        // Отобразить список отелей
//    }
//
//    override fun showError(message: String) {
//        // Показать ошибку
//    }
//}

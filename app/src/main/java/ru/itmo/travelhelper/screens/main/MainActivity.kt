package ru.itmo.travelhelper.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityMainBinding
import ru.itmo.travelhelper.screens.activities.ActivitiesActivity
import ru.itmo.travelhelper.screens.hotels.HotelActivity
import ru.itmo.travelhelper.view.main.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomMenuMain

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


    }
}
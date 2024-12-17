package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityMainBinding
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



//        navView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.timetableItem -> {
//                    val action = MainTimetableFragment.actionToProfileFragment()
//                    action.arg1 = "user_id"
//                    action.arg2 = "username"
//                    findNavController().navigate(action)
//                    true
//                }
//                R.id.expressItem -> {
//                    val action = MainExpressFragment.actionHomeFragmentToSettingsFragment()
//                    action.arg3 = "setting_value"
//                    findNavController().navigate(action)
//                    true
//                }
//                R.id.expressItem -> {
//                    val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
//                    action.arg3 = "setting_value"
//                    findNavController().navigate(action)
//                    true
//                }
//                else -> false
//            }
//        }

    }
}
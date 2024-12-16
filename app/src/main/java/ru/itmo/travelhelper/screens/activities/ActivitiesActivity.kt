package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.databinding.ActivityMainBinding

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Установка стартового фрагмента
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.navHostFragment.id, CategoryFragment())
                .commit()
        }
    }

    // Метод для замены фрагментов
    fun replaceFragment(fragment: androidx.fragment.app.Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.navHostFragment.id, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}

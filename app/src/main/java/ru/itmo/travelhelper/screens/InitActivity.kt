package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.screens.flightScreens.FlightActivity


class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isFirstLaunching = false //get bool value if first launching true/false

        if (isFirstLaunching) {
            val intentWelcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(intentWelcomeActivity)
        }
        else {
//            val intentMainActivity = Intent(this, MainActivity::class.java)
            val intentMainActivity = Intent(this, FlightActivity::class.java)
            startActivity(intentMainActivity)
        }
        finish()


    }
}
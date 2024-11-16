package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isFirstLaunching = true //get bool value if first launching true/false

        if (isFirstLaunching) {
            val intentWelcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(intentWelcomeActivity)
        }
        else {
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
        }
        finish()


    }
}
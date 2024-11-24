package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.databinding.ActivityMainBinding


class InitActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)
        val isFirstLaunching = true //get bool value if first launching true/false

        if (isFirstLaunching) {
            val intentWelcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(intentWelcomeActivity)
        } else {
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
        }
        finish()


    }
}
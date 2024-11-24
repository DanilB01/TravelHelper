package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.data.prefs.FirstLaunchCheckerRepositoryImpl
import ru.itmo.domain.usecases.CheckFirstLaunchUseCase

class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isFirstLaunching =
            CheckFirstLaunchUseCase(FirstLaunchCheckerRepositoryImpl(this@InitActivity)).execute()

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
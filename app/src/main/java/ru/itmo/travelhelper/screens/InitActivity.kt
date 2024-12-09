package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.presenter.InitPresenter
import ru.itmo.travelhelper.view.InitView


class InitActivity : AppCompatActivity(), InitView {

    private val initPresenter: InitPresenter by lazy { InitPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isFirstLaunching = initPresenter.isFirstLaunch(this@InitActivity)


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
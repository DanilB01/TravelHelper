package ru.itmo.travelhelper.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.databinding.ActivityMainBinding


class InitActivity : AppCompatActivity() {
    private val mHandler = Handler(Looper.getMainLooper())
    private val mLauncher: Launcher = Launcher()
    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        mHandler.postDelayed(mLauncher, SPLASH_DELAY.toLong())
    }

    override fun onStop() {
        mHandler.removeCallbacks(mLauncher)
        super.onStop()
    }

    private fun launch() {
        if (!isFinishing) {
            startActivity(Intent(this@InitActivity, HotelActivity::class.java))
            finish()
        }
    }


    private inner class Launcher : Runnable {
        override fun run() {
            launch()
        }
    }

    companion object {
        private const val SPLASH_DELAY = 2000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)
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
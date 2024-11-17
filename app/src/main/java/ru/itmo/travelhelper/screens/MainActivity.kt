package ru.itmo.travelhelper.screens


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.databinding.ActivityMainBinding

import android.content.Intent
import android.os.Handler
import android.os.Looper


class MainActivity : AppCompatActivity() {
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
            startActivity(Intent(this@MainActivity, HotelActivity::class.java))
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

    }

}
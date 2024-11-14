package ru.itmo.travelhelper.screens

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.view.InitView

class InitScreen : AppCompatActivity(), InitView {
    private lateinit var textInitView: TextView
    private lateinit var titleInitView: TextView
    private lateinit var imageInitView: ImageView
    private lateinit var ButtonNextInit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_screen)

        titleInitView = findViewById(R.id.titleTextInit)
        textInitView = findViewById(R.id.mainTextInit)
        imageInitView = findViewById(R.id.imageInit)
        ButtonNextInit = findViewById(R.id.nextButtonInit)

        val isFirstLaunching = true //get bool value if first launching true/false
        if (!isFirstLaunching) {
            //Intent to another activity if not first launching
        }

        ButtonNextInit.setOnClickListener {
            setNextScreen()
        }
    }

    override fun setNextScreen() {
        titleInitView.setText("123")
        TODO("Not yet implemented")
    }




}

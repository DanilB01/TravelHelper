package ru.itmo.travelhelper.screens


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.presenter.InitPresenter
import ru.itmo.travelhelper.view.InitView



class InitScreen : AppCompatActivity(), InitView {
    private lateinit var initPresenter: InitPresenter

    private lateinit var textInitView: TextView
    private lateinit var titleInitView: TextView
    private lateinit var imageInitView: ImageView
    private lateinit var buttonNextInit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_screen)

        titleInitView = findViewById(R.id.titleTextInit)
        textInitView = findViewById(R.id.mainTextInit)
        imageInitView = findViewById(R.id.imageInit)
        buttonNextInit = findViewById(R.id.nextButtonInit)

        initPresenter = InitPresenter(this)

        val isFirstLaunching = true //get bool value if first launching true/false
        if (!isFirstLaunching) {
            TODO("Intent to another activity if not first launching")
        }

        var currentScreenInitNumber = 0
        initPresenter.setNextScreen(currentScreenInitNumber)

        buttonNextInit.setOnClickListener {
            if (currentScreenInitNumber < 3) {initPresenter.setNextScreen(++currentScreenInitNumber)}

        }

    }

    override fun showNextTitleAndTextInit(initScreenNumber: Int) {
        val titleArray = resources.getStringArray(R.array.init_array_titles)
        titleInitView.text = titleArray[initScreenNumber]

        val textArray = resources.getStringArray(R.array.init_array_texts)
        textInitView.text = textArray[initScreenNumber]
    }

    override fun showNextImageInit(initScreenNumber: Int) {
        val imageResource: Int = resources.getIdentifier("@drawable/init_picture_${initScreenNumber+1}", null, packageName)
        val imageView = ContextCompat.getDrawable(this, imageResource)
        imageInitView.setImageDrawable(imageView)
    }


    override fun showNextButtonTextInit(initScreenNumber: Int) {
        val buttonArray = resources.getStringArray(R.array.init_array_buttons)
        buttonNextInit.text = buttonArray[if (initScreenNumber < 3) 0 else 1]

        val radioButtonResource = resources.getIdentifier("radioButton${initScreenNumber+1}_Init", "id", packageName)
        val radioButton = findViewById<RadioButton>(radioButtonResource)
        radioButton.isChecked = true
    }




}

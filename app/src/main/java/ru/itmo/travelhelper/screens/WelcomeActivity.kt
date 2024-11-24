package ru.itmo.travelhelper.screens


import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.ActivityWelcomeScreenBinding
import ru.itmo.travelhelper.presenter.WelcomePresenter
import ru.itmo.travelhelper.view.InitView
import ru.itmo.data.prefs.FirstLaunchCheckerRepositoryImpl
import ru.itmo.domain.usecases.CompleteFirstLaunchUseCase


class WelcomeActivity : AppCompatActivity(), InitView {
    private lateinit var welcomePresenter: WelcomePresenter

    private val binding by lazy {
        ActivityWelcomeScreenBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        welcomePresenter = WelcomePresenter(this)


        var currentScreenInitNumber = 0
        welcomePresenter.setNextScreen(currentScreenInitNumber)

        binding.nextButtonInit.setOnClickListener {
            if (currentScreenInitNumber < 3) {
                welcomePresenter.setNextScreen(++currentScreenInitNumber)
            } else {
                startMainActivity()
                CompleteFirstLaunchUseCase(FirstLaunchCheckerRepositoryImpl(this@WelcomeActivity)).execute()
            }
        }

        binding.imageCloseButton.setOnClickListener {
            startMainActivity()
            CompleteFirstLaunchUseCase(FirstLaunchCheckerRepositoryImpl(this@WelcomeActivity)).execute()
        }

    }

    private fun startMainActivity() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentMainActivity)
        finish()
    }

    override fun showNextTitleAndTextInit(initScreenNumber: Int) {
        val titleArray = resources.getStringArray(R.array.init_array_titles)
        binding.titleTextInit.text = titleArray[initScreenNumber]

        val textArray = resources.getStringArray(R.array.init_array_texts)
        binding.mainTextInit.text = textArray[initScreenNumber]
    }

    override fun showNextImageInit(initScreenNumber: Int) {
        val imageResource: Int = resources.getIdentifier("@drawable/init_picture_${initScreenNumber+1}", null, packageName)
        val imageView = ContextCompat.getDrawable(this, imageResource)
        binding.imageInit.setImageDrawable(imageView)
    }


    override fun showNextButtonTextInit(initScreenNumber: Int) {
        val buttonArray = resources.getStringArray(R.array.init_array_buttons)
        binding.nextButtonInit.text = buttonArray[if (initScreenNumber < 3) 0 else 1]

        binding.welcomeRadioGroup.check(
            resources.getIdentifier("welcomeRadioButton${initScreenNumber+1}", "id", packageName)
        )
    }

}

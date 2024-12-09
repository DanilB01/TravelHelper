package ru.itmo.travelhelper.screens.hotels

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.domain.models.Hotel
import ru.itmo.travelhelper.screens.hotels.DateSelectionFragment
import ru.itmo.travelhelper.screens.hotels.HotelSelectionFragment
import ru.itmo.travelhelper.screens.hotels.RoomSelectionFragment
import ru.itmo.travelhelper.screens.hotels.SkipHotelSelectionFragment
import ru.itmo.travelhelper.databinding.ActivityHotelBinding
import ru.itmo.travelhelper.presenter.hotels.HotelPresenter
import ru.itmo.travelhelper.screens.MainActivity
import ru.itmo.travelhelper.view.HotelView

class HotelActivity : AppCompatActivity(), HotelView {
    private val presenter: HotelPresenter by lazy { HotelPresenter(this) }
    private lateinit var binding: ActivityHotelBinding
    private var currentFragmentNumber: Int = 0
    private var maxFragmentNumber: Int = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.setupView()

        openFragment(SkipHotelSelectionFragment.newInstance())
        binding.buttonBack.setOnClickListener {

            openPrevFragment()
        }
        binding.buttonNext.setOnClickListener {

            openNextFragment()
        }
    }

    fun openNextFragment() {
        if (currentFragmentNumber < maxFragmentNumber) {
            currentFragmentNumber++
            if (currentFragmentNumber == 1) {
                binding.buttonNext.setVisibility(View.VISIBLE)
                binding.buttonBack.setVisibility(View.VISIBLE)
            }
            openFragment(chooseFragment(currentFragmentNumber))

        } else {
            openNextActivity()
        }
    }

    fun openPrevFragment() {
        if (currentFragmentNumber < maxFragmentNumber) {
            currentFragmentNumber--
            if (currentFragmentNumber == 0) {
                binding.buttonNext.setVisibility(View.INVISIBLE)
                binding.buttonBack.setVisibility(View.INVISIBLE)
            }
            openFragment(chooseFragment(currentFragmentNumber))

        }
    }

    private fun openNextActivity() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentMainActivity)

    }


    open

    override fun showLoading() {
        // Показать индикатор загрузки
    }

    override fun hideLoading() {
        // Скрыть индикатор загрузки
    }

    override fun showHotels(hotels: List<Hotel>) {
        // Отобразить список отелей
    }

    override fun showError(message: String) {
        // Показать ошибку
    }

    fun chooseFragment(idFragment: Int): Fragment {
        var fragmentChosen: Fragment = SkipHotelSelectionFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = SkipHotelSelectionFragment.newInstance()
            1 -> fragmentChosen = DateSelectionFragment.newInstance()
            2 -> fragmentChosen = HotelSelectionFragment.newInstance()
            3 -> fragmentChosen = RoomSelectionFragment.newInstance()
        }
        return fragmentChosen
    }


    fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }


}

package ru.itmo.travelhelper.screens.hotels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.domain.models.hotelModels.Hotel
import ru.itmo.travelhelper.databinding.ActivityHotelBinding
import ru.itmo.travelhelper.presenter.hotels.HotelPresenter
import ru.itmo.travelhelper.screens.MainActivity
import ru.itmo.travelhelper.view.hotel.HotelView

class HotelActivity : AppCompatActivity(), HotelView {
    private val presenter: HotelPresenter by lazy { HotelPresenter(this) }
    private lateinit var binding: ActivityHotelBinding
    private var currentFragmentNumber: Int = 0
    private var maxFragmentNumber: Int = 4


    private var checkInDate: String = ""
    private var checkOutDate: String = ""
    private var hotelName: String = ""
    private var hotelDescription = "HOTEL DESC"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.setupView()

        openFragment(HotelSkipSelectionFragment.newInstance())
        binding.buttonBack.setOnClickListener {

            openPrevFragment()
        }
        binding.buttonNext.setOnClickListener {

            openNextFragment()
        }
        supportFragmentManager.setFragmentResultListener(
            "requestFromHotelDateSelectionFragmentToActivity",
            this
        ) { _, result ->
            checkInDate = result.getString("CheckInDate", "NONE")
            checkOutDate = result.getString("CheckOutDate", "NONE")
            Log.i("CHECKDATA", result.getString("CheckInDate", "NONE"))
            Log.i("CHECKDATA", result.getString("CheckOutDate", "NONE"))
            binding.buttonNext.isEnabled = true

        }
        supportFragmentManager.setFragmentResultListener(
            "requestFromHotelSelectionFragmentToActivity",
            this
        ) { _, result ->
            hotelName = result.getString("Name", "NONE")
            val toast = Toast.makeText(
                this, result.getString("Name", "NONE"), Toast.LENGTH_SHORT
            )
            toast.show()
            binding.buttonNext.isEnabled = true
            openNextFragment()

        }
        supportFragmentManager.setFragmentResultListener(
            "requestFromRoomSelectionFragmentToActivity",
            this
        ) { _, result ->
            hotelName = result.getString("Name", "NONE")
            val toast = Toast.makeText(
                this, result.getString("roomName", "NONE"), Toast.LENGTH_SHORT
            )
            toast.show()


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
        if (currentFragmentNumber <= maxFragmentNumber) {
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
        var fragmentChosen: Fragment = HotelSkipSelectionFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = HotelSkipSelectionFragment.newInstance()
            1 -> fragmentChosen = HotelDateSelectionFragment.newInstance()
            2 -> fragmentChosen = HotelSelectionFragment.newInstance()
            3 -> fragmentChosen = HotelDetailsFragment.newInstance(hotelName, hotelDescription)
            4 -> fragmentChosen = HotelRoomSelectionFragment.newInstance(hotelName)
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

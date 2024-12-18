package ru.itmo.travelhelper.screens.hotels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.R
import ru.itmo.domain.models.hotel.Hotel
import ru.itmo.domain.models.hotel.Room
import ru.itmo.travelhelper.databinding.ActivityHotelBinding
import ru.itmo.travelhelper.presenter.hotels.HotelPresenter
import ru.itmo.travelhelper.screens.activities.ActivitiesActivity
import ru.itmo.travelhelper.screens.main.MainActivity
import ru.itmo.travelhelper.screens.totalinfo.TotalInfoActivity
import ru.itmo.travelhelper.view.hotel.HotelView

class HotelActivity : AppCompatActivity(), HotelView {
    private val presenter: HotelPresenter by lazy { HotelPresenter(this) }
    private lateinit var binding: ActivityHotelBinding
    private var currentFragmentNumber: Int = 0
    private var maxFragmentNumber: Int = 4
    private var isRoomChosen: Boolean = false
    private var isEditableMode: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isEditableMode = intent.getBooleanExtra("isEditableMode", false)

        if (isEditableMode) {
            openNextFragment()
        }
        else {
            openFragment(HotelSkipSelectionFragment.newInstance())
        }


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
            val checkInDate = result.getString("CheckInDate", "NONE")
            val checkOutDate = result.getString("CheckOutDate", "NONE")
            presenter.setCheckInDate(checkInDate)
            presenter.setCheckOutDate(checkOutDate)
            presenter.setVisitorsCount(result.getInt("VisitorsCount"))

            binding.buttonNext.isEnabled = true

        }
        supportFragmentManager.setFragmentResultListener(
            "requestFromHotelSelectionFragmentToActivity",
            this
        ) { _, result ->

            val hotel: Hotel = result.getSerializable("SelectedHotelModel") as Hotel


            presenter.setSelectedHotelModel(hotel)
            val toast = Toast.makeText(
                this, hotel.getHotelName(), Toast.LENGTH_SHORT
            )
            toast.show()
            binding.buttonNext.isEnabled = true
            isRoomChosen = false
            openNextFragment()

        }
        supportFragmentManager.setFragmentResultListener(
            "requestFromRoomSelectionFragmentToActivity",
            this
        ) { _, result ->
            val room: Room = result.getSerializable("SelectedRoomModel") as Room

            presenter.setSelectedRoomModel(room)
            val toast = Toast.makeText(
                this, room.getRoomName(), Toast.LENGTH_SHORT
            )
            toast.show()
            isRoomChosen = true


        }
    }

    override fun openNextFragment() {
        if (currentFragmentNumber < maxFragmentNumber) {
            currentFragmentNumber++
            if (currentFragmentNumber == 1) {
                binding.buttonNext.setVisibility(View.VISIBLE)
                binding.buttonBack.setVisibility(View.VISIBLE)
            }
            openFragment(chooseFragment(currentFragmentNumber))
//
        } else if (currentFragmentNumber == 4 && !isRoomChosen) {
            Toast.makeText(this, "Пожалуйста, выберите комнату", Toast.LENGTH_SHORT).show()
            }

        else {   openNextActivity()    }

    }

    override fun openPrevFragment() {
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
        if (isEditableMode) {
            startActivity(Intent(this, TotalInfoActivity::class.java))
        }
        else {
            startActivity(Intent(this, ActivitiesActivity::class.java))
        }

    }

    override fun chooseFragment(idFragment: Int): Fragment {
        var fragmentChosen: Fragment = HotelSkipSelectionFragment.newInstance()
        when (idFragment) {
            0 -> fragmentChosen = HotelSkipSelectionFragment.newInstance()
            1 -> fragmentChosen = HotelDateSelectionFragment.newInstance()
            2 -> fragmentChosen = HotelSelectionFragment.newInstance()
            3 -> {
                fragmentChosen = HotelDetailsFragment.newInstance(presenter.getSelectedHotelModel())
                binding.buttonNext.isEnabled = true
            }

            4 -> fragmentChosen =
                HotelRoomSelectionFragment.newInstance(presenter.getSelectedHotelModel())
        }

        return fragmentChosen
    }


    override fun openFragment(fragment: Fragment) {
        binding.buttonNext.isEnabled = (currentFragmentNumber == 3 || currentFragmentNumber == 4)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }


}

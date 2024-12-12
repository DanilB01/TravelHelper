package ru.itmo.travelhelper.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentHotelDateSelectionBinding
import java.util.Date
import android.widget.Toast
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.util.Log
import androidx.core.os.bundleOf

class HotelDateSelectionFragment : Fragment() {

    private var _binding: FragmentHotelDateSelectionBinding? = null
    private val binding get() = _binding!!
    private var checkInDate: String = "01.01.1970"
    private var isCheckInDateSelected: Boolean = false
    private var checkOutDate: String = ""
    private var isCheckOutDateSelected: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelDateSelectionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewCheckInDate.setOnClickListener {
            SetViewVisibility(binding.constraintLayoutDatePickerCheckIn, true)
            SetViewEnabled(binding.textViewCheckOutDate, false)
        }

        binding.buttonConfirmCheckInDate.setOnClickListener {
            SetViewVisibility(binding.constraintLayoutDatePickerCheckIn, false)
            SetViewEnabled(binding.textViewCheckOutDate, true)
        }
        binding.textViewCheckOutDate.setOnClickListener {
            SetViewVisibility(binding.constraintLayoutDatePickerCheckOut, true)
            SetViewEnabled(binding.textViewCheckInDate, false)
        }
        binding.buttonConfirmCheckOutDate.setOnClickListener {
            SetViewVisibility(binding.constraintLayoutDatePickerCheckOut, false)
            SetViewEnabled(binding.textViewCheckInDate, true)
        }
        binding.calendarViewCheckIn.setOnDateChangeListener { _, year, month, day ->

            val chosenDataText = "${day.toString().padStart(2, '0')}.${
                (month + 1).toString().padStart(2, '0')
            }.$year"
            val currentDate = Date()
            if (currentDate > SimpleDateFormat("dd.MM.yyyy").parse(chosenDataText)) {
                val toast = Toast.makeText(
                    context, "Пожалуйста выберите дату не раньше текущей", Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                if (isValidDate(chosenDataText)) {
                    onCheckInDateSelected(chosenDataText)


                } else {
                    val toast = Toast.makeText(
                        context,
                        "Неверный формат даты",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }

        binding.calendarViewCheckOut.setOnDateChangeListener { _, year, month, day ->

            val chosenDataText = "${day.toString().padStart(2, '0')}.${
                (month + 1).toString().padStart(2, '0')
            }.$year"
            val currentDate = Date()
            if (currentDate > SimpleDateFormat("dd.MM.yyyy").parse(chosenDataText)) {
                val toast = Toast.makeText(
                    context, "Пожалуйста выберите дату не раньше текущей", Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                if (isValidDate(chosenDataText) && isFirstDateLessThanSecond(
                        checkInDate, chosenDataText
                    )
                ) {
                    onCheckOutDateSelected(chosenDataText)


                } else {
                    val toast = Toast.makeText(
                        context,
                        "Пожалуйста выберите дату отъезда не раньше, чем дату заселения",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }
    }


    private fun onCheckInDateSelected(chosenDate: String) {
        checkInDate = chosenDate
        binding.textViewCheckInDate.text = chosenDate
        isCheckInDateSelected = true
        trySendDateToActivity()

    }

    private fun onCheckOutDateSelected(chosenDate: String) {
        checkOutDate = chosenDate
        binding.textViewCheckOutDate.text = chosenDate
        isCheckOutDateSelected = true
        trySendDateToActivity()


    }
    private fun trySendDateToActivity()
    {
        if(isCheckInDateSelected && isCheckOutDateSelected)
        {
            parentFragmentManager.setFragmentResult("requestFromHotelDateSelectionFragmentToActivity", bundleOf("CheckInDate" to checkInDate, "CheckOutDate" to checkOutDate))

        }
    }

    private fun SetViewVisibility(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE

        } else {
            view.visibility = View.INVISIBLE

        }
    }

    private fun SetViewEnabled(view: View, enabled: Boolean) {
        view.isEnabled = enabled
    }


    private fun isValidDate(dateStr: String): Boolean {

        if (dateStr.isNotEmpty() && dateStr.length == 10) {
            return try {

                val date = SimpleDateFormat("dd.MM.yyyy").parse(dateStr)
                return true
            } catch (e: ParseException) {
                false
            }
        } else {
            return false
        }
    }

    private fun isFirstDateLessThanSecond(dateStr1: String, dateStr2: String): Boolean {
        //Проверка 1 дата раньше 2
        if (isValidDate(dateStr1) && isValidDate(dateStr2)) {
            val date1 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr1)
            val date2 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr2)
            return date1 <= date2
        } else {
            throw IllegalArgumentException("Неверный формат даты: $dateStr1 или $dateStr2")
        }
    }
    override fun onDestroy()
    {
        super.onDestroy()
        Log.d("DESTROY", "DateFragmentDestroyed")
        parentFragmentManager.setFragmentResult("requestHotelDateToActivityFromFragment", bundleOf("CheckInDate" to checkInDate, "CheckOutDate" to checkOutDate))

    }
    companion object {

        fun newInstance() = HotelDateSelectionFragment()
    }

}
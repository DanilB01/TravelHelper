package ru.itmo.travelhelper.screens.flight


import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import ru.itmo.travelhelper.databinding.FragmentFlightDateBinding
import ru.itmo.travelhelper.presenter.flight.FlightDatePresenter
import ru.itmo.travelhelper.screens.flight.model.DateInputMask
import java.util.Date


class FlightDateFragment : Fragment() {
    private val presenter: FlightDatePresenter by lazy { FlightDatePresenter(this) }
    lateinit var binding: FragmentFlightDateBinding


    private var localDepartureDataList: MutableList<String> = mutableListOf("","","")
    private var localArrivalDataList: MutableList<String> = mutableListOf("","","")
    private var isDatePickerThereVisible: Boolean = false
    private var isDatePickerReturnVisible: Boolean = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightDateBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightDateFragment()
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Получение данных с активити
        parentFragmentManager.setFragmentResultListener("requestFlightToDateFromActivity", this) { _, result ->
            result.getStringArrayList("DateDataListFromAct")?.let { presenter.updateFullSavedDateData(it.toMutableList()) }
            this.localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localArrivalDataList = result.getStringArrayList("ArrivalDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            binding.locationArrivalDataText.text = localArrivalDataList.joinToString(", ")
            binding.locationDepartureDataText.text = localDepartureDataList.joinToString(", ")
        }

        binding.datePickerThere.visibility = View.GONE
        binding.datePickerReturn.visibility = View.GONE
        binding.layoutDateReturn.visibility = View.GONE
        binding.dateFormatHintReturnText.visibility = View.GONE

        binding.datePickerThere.setOnDateChangeListener { _, year, month, day ->
            binding.editTextDateThere.setText("$day.${month+1}.$year")
        }
        binding.datePickerReturn.setOnDateChangeListener { _, year, month, day ->
            binding.editTextDateReturn.setText("$day.${month+1}.$year")
        }

        binding.editTextDateThere.addTextChangedListener(DateInputMask(binding.editTextDateThere))
        binding.editTextDateReturn.addTextChangedListener(DateInputMask(binding.editTextDateReturn))


        binding.imgButtonOpenDatePickerThere.setOnClickListener {
            if (isDatePickerThereVisible) {
                binding.datePickerThere.visibility = View.GONE
                isDatePickerThereVisible = false
            }
            else {
                binding.datePickerThere.visibility = View.VISIBLE
                isDatePickerThereVisible = true
            }
        }

        binding.imgButtonOpenDatePickerReturn.setOnClickListener {
            if (isDatePickerReturnVisible) {
                binding.datePickerReturn.visibility = View.GONE
                isDatePickerReturnVisible = false
            }
            else {
                binding.datePickerReturn.visibility = View.VISIBLE
                isDatePickerReturnVisible = true
            }
        }


        binding.radioButtonDateReturn.setOnClickListener {
            if (binding.radioButtonDateReturn.isChecked) {
                binding.layoutDateReturn.visibility = View.VISIBLE
                binding.dateFormatHintReturnText.visibility = View.VISIBLE
            } else {
                binding.layoutDateReturn.visibility = View.GONE
                binding.dateFormatHintReturnText.visibility = View.GONE
            }
            binding.datePickerReturn.visibility = View.GONE
            isDatePickerReturnVisible = false
        }

        binding.testButtonDate.setOnClickListener {
            if (checkEditTextValidDate()) {
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDateBool",
                    bundleOf("isDateFull" to true))
            }
            else {
                parentFragmentManager.setFragmentResult("requestFlightToActivityFromDateBool",
                    bundleOf("isDateFull" to false))
            }
        }


    }

    private fun checkEditTextValidDate(): Boolean {
        val dataThere = binding.editTextDateThere.text.toString()
        val dataReturn = binding.editTextDateReturn.text.toString()
        val currentDate = Date()
        if (!isValidDate(dataThere) || currentDate > SimpleDateFormat("dd.MM.yyyy").parse(dataThere)) {
            return false
        }
        if (isValidDate(dataThere) && !binding.radioButtonDateReturn.isChecked) {
            return true }

        if (isValidDate(dataReturn) && compareDates(dataReturn, dataThere)) {
            return true
        }
        else {
            return false
        }

    }



    private fun isValidDate(dateStr: String): Boolean {
        if (dateStr.isNotEmpty() && dateStr.length == 10) {
        return try {
            val date = SimpleDateFormat("dd.MM.yyyy").parse(dateStr)
            SimpleDateFormat("dd.MM.yyyy").format(date) == dateStr
        } catch (e: ParseException) {
            false
        } }
        else {
            return false
        }
    }

    private fun compareDates(dateStr1: String, dateStr2: String): Boolean {
        if (isValidDate(dateStr1) && isValidDate(dateStr2)) {
            val date1 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr1)
            val date2 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr2)
            return date1 >= date2
        } else {
            throw IllegalArgumentException("Неверный формат даты: $dateStr1 или $dateStr2")
        }
    }



}




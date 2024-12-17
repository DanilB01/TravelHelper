package ru.itmo.travelhelper.screens.flight


import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.itmo.travelhelper.databinding.FragmentFlightDateBinding
import ru.itmo.travelhelper.presenter.flight.FlightDatePresenter
import ru.itmo.travelhelper.view.flight.FlightDateView
import java.util.Date


class FlightDateFragment : Fragment(), FlightDateView {
    private val presenter: FlightDatePresenter by lazy { FlightDatePresenter(this) }
    lateinit var binding: FragmentFlightDateBinding


    private var localDepartureDataList: MutableList<String> = mutableListOf("","","")
    private var localArrivalDataList: MutableList<String> = mutableListOf("","","")


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные в активити при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromDate", bundleOf("DateListData" to presenter.giveDateData()))
    }


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


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Получение данных с активити
        parentFragmentManager.setFragmentResultListener("requestFlightToDateFromActivity", this) { _, result ->
            result.getStringArrayList("DateDataListFromAct")?.let { presenter.updateFullSavedDateData(it.toMutableList()) }
            this.localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localArrivalDataList = result.getStringArrayList("ArrivalDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            binding.locationArrivalDataText.text = localArrivalDataList.joinToString(", ")
            binding.locationDepartureDataText.text = localDepartureDataList.joinToString(", ")
            result.getStringArrayList("DateDataListFromAct")?.let { presenter.updateFullSavedDateData(it.toMutableList()) }
            setSavedDateData(presenter.giveDateData())
        }

        binding.datePickerThere.visibility = View.GONE
        binding.datePickerReturn.visibility = View.GONE


        binding.buttonStartPickDateThere.setOnClickListener {
            binding.buttonStartPickDateThere.visibility = View.GONE
            binding.layoutDateThere.visibility = View.VISIBLE
            binding.dateFormatHintThereText.visibility = View.VISIBLE
            binding.datePickerThere.visibility = View.VISIBLE

            if (binding.radioButtonDateReturn.isChecked && binding.buttonStartPickDateReturn.visibility == View.GONE)
            {
                binding.layoutDateReturn.visibility = View.GONE
                binding.dateFormatHintReturnText.visibility = View.GONE
                binding.datePickerReturn.visibility = View.GONE

                if (isValidDate(binding.buttonPickDateReturn.text.toString()))
                {
                    binding.buttonStartPickDateReturn.text = binding.buttonPickDateReturn.text
                }
                else
                {
                    binding.buttonStartPickDateReturn.text = "Нажмите, чтобы выбрать дату"
                }
                binding.buttonStartPickDateReturn.visibility = View.VISIBLE
            }

        }

        binding.buttonStartPickDateReturn.setOnClickListener {
            binding.buttonStartPickDateReturn.visibility = View.GONE
            binding.layoutDateReturn.visibility = View.VISIBLE
            binding.dateFormatHintReturnText.visibility = View.VISIBLE
            binding.datePickerReturn.visibility = View.VISIBLE


            if (binding.buttonStartPickDateThere.visibility == View.GONE) {
                binding.layoutDateThere.visibility = View.GONE
                binding.dateFormatHintThereText.visibility = View.GONE
                binding.datePickerThere.visibility = View.GONE

                if (isValidDate(binding.buttonPickDateThere.text.toString()))
                {
                    binding.buttonStartPickDateThere.text = binding.buttonPickDateThere.text
                }
                else
                {
                    binding.buttonStartPickDateThere.text = "Нажмите, чтобы выбрать дату"
                }
                binding.buttonStartPickDateThere.visibility = View.VISIBLE
            }

        }


        binding.datePickerThere.setOnDateChangeListener { _, year, month, day ->
            val textFromDateReturn = binding.buttonStartPickDateReturn.text.toString()
            val chosenDataText = "${day.toString().padStart(2,'0')}.${(month+1).toString().padStart(2,'0')}.$year"
            val currentDate = Date()
            if (currentDate > SimpleDateFormat("dd.MM.yyyy").parse(chosenDataText)) {
                val toast = Toast.makeText(context, "Пожалуйста выберите дату не раньше текущей",
                    Toast.LENGTH_SHORT)
                toast.show()
            }
            else
            {
                if (isValidDate(textFromDateReturn) && compareDates(textFromDateReturn,chosenDataText)
                    || !isValidDate(textFromDateReturn)
                    || !binding.radioButtonDateReturn.isChecked)
                {
                    binding.buttonPickDateThere.text = chosenDataText
                    binding.buttonStartPickDateThere.text = chosenDataText
                    presenter.updateExactIndexSavedDateData(chosenDataText, 0)
                    sendIsDateFullToAct()

                    binding.buttonStartPickDateThere.visibility = View.VISIBLE
                    binding.layoutDateThere.visibility = View.GONE
                    binding.dateFormatHintThereText.visibility = View.GONE
                    binding.datePickerThere.visibility = View.GONE
                }
                else
                {
                    val toast = Toast.makeText(
                        context, "Пожалуйста выберите дату вылета не раньше, чем дату прилета",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }

        binding.datePickerReturn.setOnDateChangeListener { _, year, month, day ->
            val textFromDateThere = binding.buttonStartPickDateThere.text.toString()
            val chosenDataText = "${day.toString().padStart(2,'0')}.${(month+1).toString().padStart(2,'0')}.$year"
            val currentDate = Date()
            if (currentDate > SimpleDateFormat("dd.MM.yyyy").parse(chosenDataText))
            {
                val toast = Toast.makeText(context, "Пожалуйста выберите дату не раньше текущей",
                    Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                if (isValidDate(textFromDateThere) && compareDates(chosenDataText, textFromDateThere)
                    || !isValidDate(textFromDateThere))
                {
                    binding.buttonPickDateReturn.text = chosenDataText
                    binding.buttonStartPickDateReturn.text = chosenDataText
                    presenter.updateExactIndexSavedDateData(chosenDataText, 1)
                    sendIsDateFullToAct()

                    binding.buttonStartPickDateReturn.visibility = View.VISIBLE
                    binding.layoutDateReturn.visibility = View.GONE
                    binding.dateFormatHintReturnText.visibility = View.GONE
                    binding.datePickerReturn.visibility = View.GONE
                } else
                {
                    val toast = Toast.makeText(
                        context, "Пожалуйста выберите дату прилета не раньше, чем дату вылета",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }

        val dateThereClickListener = View.OnClickListener {
            binding.datePickerThere.visibility = View.GONE
            binding.layoutDateThere.visibility = View.GONE
            binding.dateFormatHintThereText.visibility = View.GONE

            if (isValidDate(binding.buttonPickDateThere.text.toString()))
            {
                binding.buttonStartPickDateThere.text = binding.buttonPickDateThere.text
            }
            else
            {
                binding.buttonStartPickDateThere.text = "Нажмите, чтобы выбрать дату"
            }
            binding.buttonStartPickDateThere.visibility = View.VISIBLE

        }

        binding.imgButtonOpenDatePickerThere.setOnClickListener(dateThereClickListener)
        binding.buttonPickDateThere.setOnClickListener(dateThereClickListener)

        val dateReturnClickListener = View.OnClickListener {
            binding.datePickerReturn.visibility = View.GONE
            binding.layoutDateReturn.visibility = View.GONE
            binding.dateFormatHintReturnText.visibility = View.GONE

            if (isValidDate(binding.buttonPickDateReturn.text.toString()))
            {
                binding.buttonStartPickDateReturn.text = binding.buttonPickDateReturn.text
            }
            else
            {
                binding.buttonStartPickDateReturn.text = "Нажмите, чтобы выбрать дату"
            }
            binding.buttonStartPickDateReturn.visibility = View.VISIBLE
        }

        binding.imgButtonOpenDatePickerReturn.setOnClickListener(dateReturnClickListener)
        binding.buttonPickDateReturn.setOnClickListener(dateReturnClickListener)


        binding.radioButtonDateReturn.setOnCheckedChangeListener { _, _ ->
            if (binding.radioButtonDateReturn.isChecked)
            {
                presenter.updateExactIndexSavedDateData("ReturnChecked_True", 2)
                binding.buttonStartPickDateReturn.visibility = View.VISIBLE
                if (isValidDate(binding.buttonStartPickDateReturn.text.toString())
                    && isValidDate(binding.buttonStartPickDateThere.text.toString())
                    && compareDatesStrict(binding.buttonStartPickDateThere.text.toString(), binding.buttonStartPickDateReturn.text.toString()))
                {
                    binding.buttonPickDateReturn.text = "Выберите дату"
                    binding.buttonStartPickDateReturn.text = "Нажмите, чтобы выбрать дату"
                }
            }
            else
            {
                presenter.updateExactIndexSavedDateData("ReturnChecked_False", 2)

                binding.buttonStartPickDateReturn.visibility = View.GONE
                binding.layoutDateReturn.visibility = View.GONE
                binding.dateFormatHintReturnText.visibility = View.GONE
                binding.datePickerReturn.visibility = View.GONE

            }
            sendIsDateFullToAct()
        }



    }
    private fun sendIsDateFullToAct() {
        if (checkEditTextValidDate()) {
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDateBool",
                bundleOf("isDateFull" to true))
        }
        else {
            parentFragmentManager.setFragmentResult("requestFlightToActivityFromDateBool",
                bundleOf("isDateFull" to false))
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun checkEditTextValidDate(): Boolean {
        val dataThere = binding.buttonPickDateThere.text.toString()
        val dataReturn = binding.buttonPickDateReturn.text.toString()
        val currentDate = Date()
        if (!isValidDate(dataThere) || (currentDate > SimpleDateFormat("dd.MM.yyyy").parse(dataThere))) {
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



    @SuppressLint("SimpleDateFormat")
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

    @SuppressLint("SimpleDateFormat")
    private fun compareDates(dateStr1: String, dateStr2: String): Boolean {
        //Проверка 1 дата позже 2
        if (isValidDate(dateStr1) && isValidDate(dateStr2)) {
            val date1 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr1)
            val date2 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr2)
            return date1 >= date2
        } else {
            throw IllegalArgumentException("Неверный формат даты: $dateStr1 или $dateStr2")
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun compareDatesStrict(dateStr1: String, dateStr2: String): Boolean {
        //Проверка 1 дата позже 2
        if (isValidDate(dateStr1) && isValidDate(dateStr2)) {
            val date1 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr1)
            val date2 = SimpleDateFormat("dd.MM.yyyy").parse(dateStr2)
            return date1 > date2
        } else {
            throw IllegalArgumentException("Неверный формат даты: $dateStr1 или $dateStr2")
        }
    }

    private fun setSavedDateData(dateDataList: MutableList<String>) {
        if (dateDataList[0].isNotEmpty()) {
            binding.buttonStartPickDateThere.text = dateDataList[0]
            binding.buttonPickDateThere.text = dateDataList[0]

        }
        else {
            binding.buttonPickDateThere.text = "Выберите дату"
            binding.buttonStartPickDateThere.text = "Нажмите, чтобы выбрать дату"
        }
        if (dateDataList[2] == "ReturnChecked_True") {
            binding.buttonStartPickDateReturn.text = dateDataList[1]
            binding.buttonPickDateReturn.text = dateDataList[1]
            binding.radioButtonDateReturn.isChecked = true
        }
        else {
            binding.buttonPickDateReturn.text = "Выберите дату"
            binding.buttonStartPickDateReturn.text = "Нажмите, чтобы выбрать дату"
        }

    }


}




package ru.itmo.travelhelper.screens.flight

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.itmo.travelhelper.databinding.FragmentFlightTicketsReturnBinding
import ru.itmo.travelhelper.presenter.flight.FlightTicketsReturnPresenter
import ru.itmo.travelhelper.screens.flight.adapter.FlightTicketsListAdapter
import ru.itmo.travelhelper.screens.flight.adapter.FlightTicketsOnItemClickListener
import ru.itmo.travelhelper.view.flight.FlightTicketsReturnView


class FlightTicketsReturnFragment : Fragment(), FlightTicketsReturnView {
    private val presenter: FlightTicketsReturnPresenter by lazy { FlightTicketsReturnPresenter(this) }
    lateinit var binding: FragmentFlightTicketsReturnBinding
    private lateinit var adapter: FlightTicketsListAdapter
    private val dataList = listOf(
        listOf("Россия","27000₽","00.12","09.47"),
        listOf("S7 Airlines","19000₽","01.45","11.23"),
        listOf("Казах Эйр","25000₽","03.34","15.09"),
        listOf("Смартавиа","1234₽","02.11","16.34"),
        listOf("Emirates","78000₽","17.44","21.00"))


    lateinit var localDepartureDataList: MutableList<String>
    lateinit var localArrivalDataList: MutableList<String>
    lateinit var localDateDataList: MutableList<String>


    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные в активити при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromTicketReturn",
            bundleOf("TicketReturnListData" to presenter.giveTicketReturnProgress())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightTicketsReturnBinding.inflate(inflater)
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = FlightTicketsReturnFragment()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Получение данных с активити
        parentFragmentManager.setFragmentResultListener("requestFlightToTicketReturnFromActivity", this) { _, result ->
            this.localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localArrivalDataList = result.getStringArrayList("ArrivalDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localDateDataList = result.getStringArrayList("DateDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            launchTicketAdapter(listOf(localArrivalDataList[2],localDepartureDataList[2]))
            presenter.updateSavedTicketReturnProgress(result.getInt("TicketReturnDataListFromAct"))
            if (presenter.giveTicketReturnProgress() == 0) {
                presenter.updateSavedTicketReturnProgress(binding.seekBar.min)
            }
            binding.seekBar.progress = presenter.giveTicketReturnProgress()
            filterData(binding.seekBar.progress)
        }





        binding.minFlightTicketCost.text = "${formatNumber(binding.seekBar.min.toString())}₽"
        binding.maxFlightTicketCost.text = "${formatNumber(binding.seekBar.max.toString())}₽"

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    showCurrentValueSnackbar(progress)
                }
                filterData(progress)
                presenter.updateSavedTicketReturnProgress(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
    private fun showCurrentValueSnackbar(progress: Int) {
        val snackbar = Snackbar.make(binding.root, formatNumber(progress.toString()), Snackbar.LENGTH_SHORT)
        val params = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.setMargins(330+getOffsetForSnackBar(progress), 30, 0, 0)
        snackbar.view.apply {
            backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
            layoutParams = params
            elevation = 0F
        }
        snackbar.setTextColor(ColorStateList.valueOf(Color.BLACK))
        snackbar.show()
    }

    private fun filterData(progress: Int) {
        val filteredList = dataList.filter { it[1].dropLast(1).toInt() <= progress }
        adapter.updateList(filteredList)
    }

    private fun formatNumber(numberStr: String): String {
        return numberStr.reversed().chunked(3).joinToString(" ").reversed()
    }


    private fun getOffsetForSnackBar(progress: Int): Int {
        val coeff = 430
        val maxVal = binding.maxFlightTicketCost.text.toString().dropLast(1).replace(" ","").toDouble()
        val minVal = binding.minFlightTicketCost.text.toString().dropLast(1).replace(" ","").toDouble()
        val pers: Double = ((progress - minVal) / (maxVal - minVal))
        return (pers * coeff).toInt()
    }

    private fun launchTicketAdapter(listOfLocations: List<String>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FlightTicketsListAdapter(emptyList(), listOfLocations,object : FlightTicketsOnItemClickListener {
            override fun onItemClicked(position: Int) {
                Toast.makeText(requireContext(), "Вы нажали на ${adapter.items[position]}", Toast.LENGTH_SHORT).show()
            }
        })
        binding.recyclerView.adapter = adapter
    }

}
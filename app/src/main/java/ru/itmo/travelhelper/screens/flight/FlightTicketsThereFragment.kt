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
import ru.itmo.domain.models.flight.TicketModel
import ru.itmo.travelhelper.databinding.FragmentFlightTicketsThereBinding
import ru.itmo.travelhelper.presenter.flight.FlightTicketsTherePresenter
import ru.itmo.travelhelper.screens.flight.adapter.FlightTicketsListAdapter
import ru.itmo.travelhelper.screens.flight.adapter.FlightTicketsOnItemClickListener
import ru.itmo.travelhelper.view.flight.FlightTicketsThereView


class FlightTicketsThereFragment : Fragment(), FlightTicketsThereView {
    private val presenter: FlightTicketsTherePresenter by lazy { FlightTicketsTherePresenter(this) }
    lateinit var binding: FragmentFlightTicketsThereBinding
    private lateinit var adapter: FlightTicketsListAdapter
    private lateinit var ticketsDataList: List<List<String>>


    lateinit var localDepartureDataList: MutableList<String>
    lateinit var localArrivalDataList: MutableList<String>
    lateinit var localDateDataList: MutableList<String>

    override fun onDestroy() {
        super.onDestroy()

        // Отправляем данные в активити при закрытии фрагмента
        parentFragmentManager.setFragmentResult("requestFlightToActivityFromTicketThere",
            bundleOf("TicketThereListData" to presenter.giveTicketThereProgress()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightTicketsThereBinding.inflate(inflater)
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = FlightTicketsThereFragment()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Получение данных с активити
        parentFragmentManager.setFragmentResultListener("requestFlightToTicketThereFromActivity", this) { _, result ->
            this.localDepartureDataList = result.getStringArrayList("DepartureDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localArrivalDataList = result.getStringArrayList("ArrivalDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            this.localDateDataList = result.getStringArrayList("DateDataListFromAct")?.toMutableList() ?: mutableListOf("","","")
            launchTicketAdapter(listOf(localDepartureDataList[2],localArrivalDataList[2]))
            presenter.loadThereTicketData(localDepartureDataList[2],localArrivalDataList[2], localDateDataList[1])
            presenter.updateSavedTicketThereProgress(result.getInt("TicketThereDataListFromAct"))
            if (presenter.giveTicketThereProgress() == 0) {
                presenter.updateSavedTicketThereProgress(binding.seekBar.min)
            }
            binding.seekBar.progress = presenter.giveTicketThereProgress()
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
                presenter.updateSavedTicketThereProgress(progress)
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
        val filteredList = ticketsDataList.filter { it[1].dropLast(1).toInt() <= progress }
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

    override fun getTickets(ticketsData: List<TicketModel>) {
        this.ticketsDataList = ticketsData
            .map { ticket -> listOf(ticket.companyName, ticket.ticketCost, ticket.flightTimeFrom, ticket.flightTimeTo) }
    }

}
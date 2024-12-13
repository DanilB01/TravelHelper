package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.main.TimeTableModel
import ru.itmo.travelhelper.databinding.FragmentMainTimetableBinding
import ru.itmo.travelhelper.presenter.main.MainTimetablePresenter
import ru.itmo.travelhelper.screens.main.adapter.MainTimetableListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainTimetableOnItemClickListener
import ru.itmo.travelhelper.view.main.MainTimetableView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainTimetableFragment : Fragment(), MainTimetableView {
    private val presenter: MainTimetablePresenter by lazy { MainTimetablePresenter(this) }
    lateinit var binding: FragmentMainTimetableBinding
    private lateinit var adapterTimetable: MainTimetableListAdapter
    private lateinit var timeTableList: List<List<String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainTimetableBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todayData = getTodayDate()
        presenter.loadTimeTableData(todayData)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterTimetable = MainTimetableListAdapter(emptyList(), object : MainTimetableOnItemClickListener {
            override fun onItemClicked(position: Int) {

            }
        })

        binding.recyclerView.adapter = adapterTimetable
        adapterTimetable.updateList(this.timeTableList)

    }

    override fun getTimeTable(timeTableData: List<TimeTableModel>) {
        this.timeTableList = timeTableData
            .map { timeTable -> listOf(timeTable.timeData, timeTable.descriptionData, timeTable.typeData) }
    }


    private fun getTodayDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate = today.format(formatter)
        return formattedDate
    }
}
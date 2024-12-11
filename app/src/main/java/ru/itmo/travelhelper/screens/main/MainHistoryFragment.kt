package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.travelhelper.databinding.FragmentMainHistoryBinding
import ru.itmo.travelhelper.screens.main.adapter.MainHistoryListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainHistoryOnItemClickListener
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesOnItemClickListener
import ru.itmo.travelhelper.screens.main.adapter.MainTimetableListAdapter

class MainHistoryFragment : Fragment() {
    lateinit var binding: FragmentMainHistoryBinding
    private lateinit var adapterHistory: MainHistoryListAdapter
    private val dataList = listOf(
        listOf("04.12.24","09.12.24","Санкт-Петербург","Сеул","150000"),
        listOf("05.12.24","10.12.24","Москва","Сеул","150000"),
        listOf("06.12.24","11.12.24","Казань","Сидней","350000"),
        listOf("07.12.24","12.12.24","Нью-Йорк","Пекин","150000"),
        listOf("08.12.24","13.12.24","Челябинск","Мадрид","250000"),)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterHistory = MainHistoryListAdapter(emptyList(), object : MainHistoryOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {

            }
        })

        binding.recyclerView.adapter = adapterHistory
        adapterHistory.updateList(dataList)
    }

}
package ru.itmo.travelhelper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.travelhelper.databinding.FragmentMainTimetableBinding
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainInterestingPlacesOnItemClickListener
import ru.itmo.travelhelper.screens.main.adapter.MainTimetableListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainTimetableOnItemClickListener

class MainTimetableFragment : Fragment() {

    lateinit var binding: FragmentMainTimetableBinding
    private lateinit var adapterTimetable: MainTimetableListAdapter
    private val dataList = listOf(
        listOf("11.40","Вылет","default"),
        listOf("14.25","Вылет","default"),
        listOf("14.40","ресторан FISHBOWL","action1"),
        listOf("15:50","Заселение","default"),
        listOf("17:00","ATTY Gallery","action2"))

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


        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterTimetable = MainTimetableListAdapter(emptyList(), object : MainTimetableOnItemClickListener {
            override fun onItemClicked(selectedItem: Int) {

            }
        })

        binding.recyclerView.adapter = adapterTimetable
        adapterTimetable.updateList(dataList)

    }
}
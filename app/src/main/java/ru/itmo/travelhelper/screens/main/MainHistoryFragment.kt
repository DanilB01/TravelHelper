package ru.itmo.travelhelper.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.domain.models.main.HistoryModel
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentMainHistoryBinding
import ru.itmo.travelhelper.presenter.main.MainHistoryPresenter
import ru.itmo.travelhelper.screens.main.adapter.MainHistoryListAdapter
import ru.itmo.travelhelper.screens.main.adapter.MainHistoryOnItemClickListener
import ru.itmo.travelhelper.screens.totalinfo.TotalInfoActivity
import ru.itmo.travelhelper.view.main.MainHistoryView

class MainHistoryFragment : Fragment(), MainHistoryView {
    private val presenter: MainHistoryPresenter by lazy { MainHistoryPresenter(this) }
    lateinit var binding: FragmentMainHistoryBinding
    private lateinit var adapterHistory: MainHistoryListAdapter
    private lateinit var historyList: List<List<String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadHistoryData()
        if (historyList.isEmpty()) {
            findNavController().navigate(R.id.action_historyItem_to_emptyTravelsItem,null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.historyItem, true)  // Очищаем стек до newFragment
                    .build())
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapterHistory = MainHistoryListAdapter(emptyList(), object : MainHistoryOnItemClickListener {
            override fun onItemClicked(position: Int) {
                startActivity(Intent(activity, TotalInfoActivity::class.java))
            }
        })

        binding.recyclerView.adapter = adapterHistory
        adapterHistory.updateList(this.historyList)
    }

    override fun getHistoryData(historyData: List<HistoryModel>) {
        this.historyList = historyData
            .map { h_item -> listOf(h_item.travelTimeFrom, h_item.travelTimeTo,
                h_item.travelPlaceFrom, h_item.travelPlaceTo, h_item.travelCost) }
    }

}
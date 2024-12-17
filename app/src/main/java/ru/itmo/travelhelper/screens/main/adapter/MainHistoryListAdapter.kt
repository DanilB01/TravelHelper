package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentMainEmptyTravelsBinding
import ru.itmo.travelhelper.databinding.MainHistoryListItemBinding


interface MainHistoryOnItemClickListener {
    fun onItemClicked(position: Int)
}

interface MainHistoryUpdateListInterface {
    fun updateList(filteredItems: List<List<String>>)
}

class MainHistoryListAdapter(
    var items: List<List<String>>,
    private val listener: MainHistoryOnItemClickListener
)
    : RecyclerView.Adapter<MainHistoryListAdapter.MainHistoryViewHolder>(),
    MainHistoryUpdateListInterface {

    // items[0] - Travel time from
    // items[1] - Travel time to
    // items[2] - Travel place from
    // items[3] - Travel place to
    // items[4] - Travel cost


    class MainHistoryViewHolder(private val binding: MainHistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List<String>) {
            binding.fieldDepartureTimeMainHistoryListItem.text = item[0]
            binding.fieldArrivalTimeMainHistoryListItem.text = item[1]
            binding.fieldDeparturePlaceMainHistoryListItem.text = item[2]
            binding.fieldArrivalPlaceMainHistoryListItem.text = item[3]
            binding.fieldTravelCostMainHistoryListItem.text = item[4]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHistoryViewHolder {
        val binding = MainHistoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHistoryViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainHistoryViewHolder, position: Int) {
        holder.bind(items[position])


        holder.itemView.setOnClickListener {
            listener.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun updateList(filteredItems: List<List<String>>) {
        this.items = filteredItems
        notifyDataSetChanged()
    }

    private fun formatNumber(numberStr: String): String {
        return numberStr.reversed().chunked(3).joinToString(" ").reversed()
    }
}
package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FragmentMainHistoryBinding
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


    class MainHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTimeDeparture: TextView = itemView.findViewById(R.id.fieldDepartureTimeMainHistoryListItem)
        val textTimeArrival: TextView = itemView.findViewById(R.id.fieldArrivalTimeMainHistoryListItem)
        val textPlaceDeparture: TextView = itemView.findViewById(R.id.fieldDeparturePlaceMainHistoryListItem)
        val textPlaceArrival: TextView = itemView.findViewById(R.id.fieldArrivalPlaceMainHistoryListItem)
        val textCostItem: TextView = itemView.findViewById(R.id.fieldTravelCostMainHistoryListItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_history_list_item, parent, false)
        return MainHistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainHistoryViewHolder, position: Int) {
        val ite = arrayListOf("1","2","3")
        holder.textTimeDeparture.text = items[position][0]
        holder.textTimeArrival.text = items[position][1]
        holder.textPlaceDeparture.text = items[position][2]
        holder.textPlaceArrival.text = items[position][3]
        holder.textCostItem.text = items[position][4]




        // Добавляем обработчик нажатия на каждый элемент
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
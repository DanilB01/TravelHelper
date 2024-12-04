package ru.itmo.travelhelper.screens.flight

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R


interface FlightTicketsOnItemClickListener {
    fun onItemClicked(position: Int)
}

interface FlightTicketsUpdateListInterface {
    fun updateList(filteredItems: List<List<String>>)
}

class FlightTicketsListAdapter(
    var items: List<List<String>>,
    private val listener: FlightTicketsOnItemClickListener)
    : RecyclerView.Adapter<FlightTicketsListAdapter.FlightTicketsViewHolder>(), FlightTicketsUpdateListInterface {


    class FlightTicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCompanyNameItem: TextView = itemView.findViewById(R.id.fieldCompanyNameTicketListItem)
        val textCostItem: TextView = itemView.findViewById(R.id.fieldCostTicketListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightTicketsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_ticket_list_item, parent, false)
        return FlightTicketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightTicketsViewHolder, position: Int) {
        holder.textCompanyNameItem.text = items[position][0]
        holder.textCostItem.text = items[position][1]

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
}
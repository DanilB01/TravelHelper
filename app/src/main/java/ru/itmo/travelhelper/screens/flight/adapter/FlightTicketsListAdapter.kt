package ru.itmo.travelhelper.screens.flight.adapter

import android.annotation.SuppressLint
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
    var place_items: List<String>,
    private val listener: FlightTicketsOnItemClickListener
)
    : RecyclerView.Adapter<FlightTicketsListAdapter.FlightTicketsViewHolder>(),
    FlightTicketsUpdateListInterface {

    // items[0] - Company name
    // items[1] - Ticket cost
    // items[2] - Flight time from
    // items[3] - Flight time to

    // place_items[0] - Flight place from
    // place_items[1] - Flight place to


    class FlightTicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCompanyNameItem: TextView = itemView.findViewById(R.id.fieldCompanyNameTicketListItem)
        val textCostItem: TextView = itemView.findViewById(R.id.fieldCostTicketListItem)
        val textTimeDeparture: TextView = itemView.findViewById(R.id.fieldDepartureTimeTicketListItem)
        val textPlaceDeparture: TextView = itemView.findViewById(R.id.fieldDeparturePlaceTicketListItem)
        val textTimeArrival: TextView = itemView.findViewById(R.id.fieldArrivalTimeTicketListItem)
        val textPlaceArrival: TextView = itemView.findViewById(R.id.fieldArrivalPlaceTicketListItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightTicketsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_ticket_list_item, parent, false)
        return FlightTicketsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlightTicketsViewHolder, position: Int) {
        holder.textCompanyNameItem.text = items[position][0]
        holder.textCostItem.text = formatNumber(items[position][1].dropLast(1))+"₽"
        holder.textTimeDeparture.text = items[position][2]
        holder.textTimeArrival.text = items[position][3]

        holder.textPlaceDeparture.text = place_items[0]
        holder.textPlaceArrival.text = place_items[1]



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
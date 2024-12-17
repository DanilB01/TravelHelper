package ru.itmo.travelhelper.screens.flight.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FlightTicketListItemBinding


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


    class FlightTicketsViewHolder(private val binding: FlightTicketListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для привязки данных
        fun bind(item: List<String>, placeItems: List<String>) {
            binding.fieldCompanyNameTicketListItem.text = item[0]

            // Форматируем цену
            val formattedCost = if (item[1].endsWith("₽")) {
                formatNumber(item[1].dropLast(1)) + "₽"
            } else {
                formatNumber(item[1]) + "₽"
            }
            binding.fieldCostTicketListItem.text = formattedCost

            binding.fieldDepartureTimeTicketListItem.text = item[2]
            binding.fieldArrivalTimeTicketListItem.text = item[3]
            binding.fieldDeparturePlaceTicketListItem.text = placeItems[0]
            binding.fieldArrivalPlaceTicketListItem.text = placeItems[1]
        }

        private fun formatNumber(numberStr: String): String {
            return numberStr.reversed().chunked(3).joinToString(" ").reversed()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightTicketsViewHolder {
        val binding = FlightTicketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightTicketsViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlightTicketsViewHolder, position: Int) {
        holder.bind(items[position], place_items)


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
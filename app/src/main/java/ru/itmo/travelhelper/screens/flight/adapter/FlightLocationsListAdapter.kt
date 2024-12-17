package ru.itmo.travelhelper.screens.flight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.FlightLocationListItemBinding

interface FlightLocationsOnItemClickListener {
    fun onItemClicked(selectedItem: Int)
}

interface FlightLocationsUpdateListInterface {
    fun updateList(filteredItems: List<String>)
}


class FlightLocationsListAdapter(
    var items: List<String>,
    private val itemClickListener: FlightLocationsOnItemClickListener
)
    : RecyclerView.Adapter<FlightLocationsListAdapter.FlightLocationViewHolder>(),
    FlightLocationsUpdateListInterface {


    class FlightLocationViewHolder(private val binding: FlightLocationListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для привязки данных
        fun bind(location: String) {
            binding.textLocationNameListItem.text = location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightLocationViewHolder {
        val binding = FlightLocationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightLocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightLocationViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun updateList(filteredItems: List<String>) {
        this.items = filteredItems
        notifyDataSetChanged()
    }


}
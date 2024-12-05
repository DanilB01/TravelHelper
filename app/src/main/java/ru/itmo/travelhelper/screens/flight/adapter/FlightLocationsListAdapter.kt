package ru.itmo.travelhelper.screens.flight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R

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


    class FlightLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationName: TextView = itemView.findViewById(R.id.textLocationNameListItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightLocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_location_list_item, parent, false)
        return FlightLocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightLocationViewHolder, position: Int) {
        holder.locationName.text = items[position]

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
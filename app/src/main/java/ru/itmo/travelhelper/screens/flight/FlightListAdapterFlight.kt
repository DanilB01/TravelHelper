package ru.itmo.travelhelper.screens.flight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

interface FlightOnItemClickListener {
    fun onItemClicked(selectedItem: String)
}

interface FlightUpdateListInterface {
    fun updateList(filteredItems: MutableList<String>)
}

class FlightListAdapterFlight(
    private val items: List<String>,
    private val itemClickListener: FlightOnItemClickListener
) : BaseAdapter(), FlightUpdateListInterface {

    private var filteredItems = items.toMutableList()

    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun updateList(filteredItems: MutableList<String>) {
        this.filteredItems = filteredItems
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = if (convertView == null) {
            LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        } else {
            convertView as TextView
        }


        view.setOnClickListener {
            itemClickListener.onItemClicked(filteredItems[position])
        }

        view.text = filteredItems[position]
        return view
    }
}
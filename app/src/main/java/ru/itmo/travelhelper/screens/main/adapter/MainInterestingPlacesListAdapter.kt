package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.MainInterestingPlaceListItemBinding


interface MainInterestingPlacesOnItemClickListener {
    fun onItemClicked(position: Int)
}

interface MainInterestingPlacesUpdateListInterface {
    fun updateList(filteredItems: List<List<String>>)
}

class MainInterestingPlacesListAdapter(
    var items: List<List<String>>,
    private val listener: MainInterestingPlacesOnItemClickListener
)
    : RecyclerView.Adapter<MainInterestingPlacesListAdapter.MainInterestingPlacesViewHolder>(),
    MainInterestingPlacesUpdateListInterface {

    // items[0] - Place name
    // items[1] - Near
    // items[2] - Details
    // items[3] - Rating

    // place_items[0] - Flight place from
    // place_items[1] - Flight place to


    class MainInterestingPlacesViewHolder(private val binding: MainInterestingPlaceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для привязки данных
        fun bind(item: List<String>) {
            binding.fieldNamePlaceListItem.text = item[0]
            binding.fieldNearPlaceListItem.text = item[1]
            binding.fieldDetailsPlaceListItem.text = item[2]
            binding.fieldRatingPlaceListItem.text = item[3]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainInterestingPlacesViewHolder {
        val binding = MainInterestingPlaceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainInterestingPlacesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainInterestingPlacesViewHolder, position: Int) {
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

}
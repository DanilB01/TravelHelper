package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R


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


    class MainInterestingPlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPlaceNameItem: TextView = itemView.findViewById(R.id.fieldNamePlaceListItem)
        val textRatingItem: TextView = itemView.findViewById(R.id.fieldRatingPlaceListItem)
        val textNearItem: TextView = itemView.findViewById(R.id.fieldNearPlaceListItem)
        val textDetailsItem: TextView = itemView.findViewById(R.id.fieldDetailsPlaceListItem)
        val imagePlaceItem: ImageView = itemView.findViewById(R.id.fieldPicturePlaceListItem)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainInterestingPlacesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_interesting_place_list_item, parent, false)
        return MainInterestingPlacesViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainInterestingPlacesViewHolder, position: Int) {
        holder.textPlaceNameItem.text = items[position][0]
        holder.textNearItem.text = items[position][1]
        holder.textDetailsItem.text = items[position][2]
        holder.textRatingItem.text = items[position][3]

//        holder.textPlaceDeparture.text = place_items[0]
//        holder.textPlaceArrival.text = place_items[1]



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
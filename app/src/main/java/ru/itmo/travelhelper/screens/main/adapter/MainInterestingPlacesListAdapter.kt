package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
    // items[4] - Picture Name


    class MainInterestingPlacesViewHolder(private val binding: MainInterestingPlaceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для привязки данных
        fun bind(item: List<String>) {
            Log.i("12345", item.toString())
            binding.fieldNamePlaceListItem.text = item[0]
            binding.fieldNearPlaceListItem.text = item[1]
            binding.fieldDetailsPlaceListItem.text = item[2]
            binding.fieldRatingPlaceListItem.text = item[3]
            binding.fieldPicturePlaceListItem.setImageDrawable(getDrawableFromResourceName(item[4]))
        }

        private fun getDrawableFromResourceName(resourceName: String): Drawable? {
            val context = binding.root.context
            val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
            Log.i("1234", resourceId.toString())
            return if (resourceId != 0) {
                ContextCompat.getDrawable(context, resourceId)
            } else {
                ContextCompat.getDrawable(context, R.drawable.init_picture_1)
            }
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
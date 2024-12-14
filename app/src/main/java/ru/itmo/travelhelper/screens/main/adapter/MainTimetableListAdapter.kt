package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.MainTimetableListItemBinding


interface MainTimetableOnItemClickListener {
    fun onItemClicked(position: Int)
}

interface MainTimetableUpdateListInterface {
    fun updateList(filteredItems: List<List<String>>)
}

class MainTimetableListAdapter(
    var items: List<List<String>>,
    private val listener: MainTimetableOnItemClickListener
)
    : RecyclerView.Adapter<MainTimetableListAdapter.MainTimetableViewHolder>(),
    MainTimetableUpdateListInterface {

    // items[0] - Date
    // items[1] - Description
    // items[2] - Action Type (default,action1,action2)


    class MainTimetableViewHolder(private val binding: MainTimetableListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: List<String>) {
            binding.fieldDateActionListItem.text = item[0]
            binding.fieldDescriptionActionListItem.text = item[1]

            val context = binding.root.context
            val bgColors = mapOf(
                "default" to context.getColor(R.color.main_timetable_default_category),
                "action1" to context.getColor(R.color.main_timetable_action1_category),
                "action2" to context.getColor(R.color.main_timetable_action2_category)
            )
            val bg_data_color = bgColors[item[2]] ?: context.getColor(R.color.main_timetable_default_category)
            binding.fieldDateActionListItem.setBackgroundColor(bg_data_color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTimetableViewHolder {
        val binding = MainTimetableListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainTimetableViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainTimetableViewHolder, position: Int) {
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
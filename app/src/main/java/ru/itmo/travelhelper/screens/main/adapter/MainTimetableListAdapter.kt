package ru.itmo.travelhelper.screens.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.R


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


    class MainTimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDateItem: TextView = itemView.findViewById(R.id.fieldDateActionListItem)
        val textDescriptionItem: TextView = itemView.findViewById(R.id.fieldDescriptionActionListItem)
        val context: Context = itemView.context



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTimetableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_timetable_list_item, parent, false)
        return MainTimetableViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainTimetableViewHolder, position: Int) {
        holder.textDateItem.text = items[position][0]
        holder.textDescriptionItem.text = items[position][1]
        Log.i("1234","here1")
        Log.i("1234","here2i")
        val bgColors = mapOf(
            "default" to holder.context.getColor(R.color.main_timetable_default_category),
            "action1" to holder.context.getColor(R.color.main_timetable_action1_category),
            "action2" to holder.context.getColor(R.color.main_timetable_action2_category)
        )
        val bg_data_color = bgColors[items[position][2]] ?: holder.context.getColor(R.color.main_timetable_default_category)
        holder.textDateItem.background = bg_data_color.toDrawable()




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
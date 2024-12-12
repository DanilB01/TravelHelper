package ru.itmo.travelhelper.screens.hotels


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.databinding.HotelCardBinding
import ru.itmo.travelhelper.R
import ru.itmo.travelhelper.databinding.RoomCardBinding

class RoomsListAdapter(val onCardClicked: (Room) -> Unit) :
    RecyclerView.Adapter<RoomsListAdapter.RoomHolder>() {
    var roomsList = ArrayList<Room>()

    class RoomHolder(view: View, val onCardClicked: (Room) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val binding = RoomCardBinding.bind(view)


        fun bind(room: Room) = with(binding)
        {

            imageView.setImageResource(room.getImageId())
            textViewRoomType.text = room.getRoomName()
            for (i in 0..<room.getIconsId().size) {
                giveImageViewByNumber(binding, i).setImageResource(room.getIconsId()[i])
            }


            mainLayout.setOnClickListener {
                onCardClicked(room)

            }


        }

        fun giveImageViewByNumber(binding: RoomCardBinding, iconNumber: Int): ImageView {
            var result = binding.imageViewIcon1
            when (iconNumber) {
                0 -> result = binding.imageViewIcon1
                1 -> result = binding.imageViewIcon2
                2 -> result = binding.imageViewIcon3
                3 -> result = binding.imageViewIcon4
                4 -> result = binding.imageViewIcon5
                5 -> result = binding.imageViewIcon6
                6 -> result = binding.imageViewIcon7
                7 -> result = binding.imageViewIcon8

            }
            return result

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_card, parent, false)

        return RoomHolder(view, onCardClicked)

    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        holder.bind(roomsList[position])
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    fun addRoom(room: Room) {
        roomsList.add(room)
        notifyDataSetChanged()
    }

    fun setNewList(list: ArrayList<Room>) {
        roomsList = list
        notifyDataSetChanged()
    }


}
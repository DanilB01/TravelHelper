package ru.itmo.travelhelper.screens.hotels


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import ru.itmo.travelhelper.databinding.HotelCardBinding
import ru.itmo.travelhelper.R

class HotelsListAdapter(val onCardClicked: (Hotel) -> Unit) :
    RecyclerView.Adapter<HotelsListAdapter.HotelHolder>() {
    var hotelsList = ArrayList<Hotel>()

    class HotelHolder(view: View, val onCardClicked: (Hotel) -> Unit) : RecyclerView.ViewHolder(view) {
        val binding = HotelCardBinding.bind(view)


        fun bind(hotel: Hotel) = with(binding)
        {

            imageView.setImageResource(hotel.getImageId())
            textViewHotelName.text = hotel.getHotelName()
            textViewCheckInTime.text = "Время - ${hotel.getCheckInTime()}"
            textViewMinPriceForNignt.text = "Минимальная стоимость за ночь:${hotel.getMinPriceString()}"
            mainLayout.setOnClickListener {
                onCardClicked(hotel)

            }


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_card, parent, false)

        return HotelHolder(view, onCardClicked)

    }

    override fun onBindViewHolder(holder: HotelHolder, position: Int) {
        holder.bind(hotelsList[position])
    }

    override fun getItemCount(): Int {
        return hotelsList.size
    }

    fun addHotel(hotel: Hotel) {
        hotelsList.add(hotel)
        notifyDataSetChanged()
    }

    fun setNewList(list: ArrayList<Hotel>) {
        hotelsList = list
        notifyDataSetChanged()
    }


}
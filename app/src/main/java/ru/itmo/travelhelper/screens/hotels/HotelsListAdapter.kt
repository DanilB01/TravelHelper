package ru.itmo.travelhelper.screens.hotels


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import ru.itmo.domain.models.hotelModels.HotelModel
import ru.itmo.travelhelper.databinding.HotelCardBinding
import ru.itmo.travelhelper.R

class HotelsListAdapter(val onCardClicked: (HotelModel) -> Unit) :
    RecyclerView.Adapter<HotelsListAdapter.HotelHolder>() {
    var hotelsList = ArrayList<HotelModel>()

    class HotelHolder(view: View, val onCardClicked: (HotelModel) -> Unit) : RecyclerView.ViewHolder(view) {
        val binding = HotelCardBinding.bind(view)


        fun bind(hotelModel: HotelModel) = with(binding)
        {

            imageView.setImageResource(hotelModel.getImageId())
            textViewHotelName.text = hotelModel.getHotelName()
            textViewCheckInTime.text = "Время - ${hotelModel.getCheckInTime()}"
            textViewMinPriceForNignt.text = "Минимальная стоимость за ночь:${hotelModel.getMinPriceString()}"
            mainLayout.setOnClickListener {
                onCardClicked(hotelModel)

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

    fun addHotel(hotelModel: HotelModel) {
        hotelsList.add(hotelModel)
        notifyDataSetChanged()
    }

    fun setNewList(list: ArrayList<HotelModel>) {
        hotelsList = list
        notifyDataSetChanged()
    }


}
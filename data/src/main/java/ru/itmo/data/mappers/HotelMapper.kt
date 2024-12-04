package ru.itmo.data.mappers

import ru.itmo.data.remote.hotel.HotelApiModel
import ru.itmo.domain.models.hotelModels.Hotel

class HotelMapper {
    fun mapFromApiModel(apiModel: HotelApiModel): Hotel {
        return Hotel(
            name = apiModel.hotel_name,
            stars = apiModel.hotel_stars,
            address = apiModel.hotel_address,
            roomsAvailable = apiModel.available_rooms
        )
    }
}
package ru.itmo.data.mappers.hotel

import ru.itmo.data.remote.hotel.HotelApiModel
import ru.itmo.domain.models.hotel.Hotel

class HotelMapper {
    fun mapFromApiModel(apiModel: HotelApiModel): Hotel {
        return Hotel(
            hotelName = apiModel.hotelName,
            checkInTime = apiModel.checkInTime,
            minPrice = apiModel.minPrice,
            description = apiModel.description,
            websiteURL = apiModel.websiteURL
        )
    }
}
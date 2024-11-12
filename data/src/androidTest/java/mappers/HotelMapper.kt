package mappers

import ru.itmo.travelhelper.data.remote.HotelApiModel
import ru.itmo.travelhelper.domain.models.Hotel

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
package ru.itmo.data.mappers.totalinfo

import ru.itmo.data.remote.totalinfo.TotalInfoPlaceApiModel
import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel

class totalInfoPlaceMapper {
    fun mapFromApiModel(apiModel: TotalInfoPlaceApiModel): TotalInfoPlaceModel {
        return TotalInfoPlaceModel(
            placeName = apiModel.place_name,
            placeLocation = apiModel.place_location,
            placeDescription = apiModel.place_description,
            placeRating = apiModel.place_rating,
            pictureName = apiModel.picture_name,
        )
    }
}
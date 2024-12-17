package ru.itmo.data.mappers.main

import ru.itmo.data.remote.main.InterestingPlaceApiModel
import ru.itmo.domain.models.main.InterestingPlaceModel

class InterestingPlaceMapper {
    fun mapFromApiModel(apiModel: InterestingPlaceApiModel): InterestingPlaceModel {
        return InterestingPlaceModel(
            placeName = apiModel.place_name,
            placeLocation = apiModel.place_location,
            placeDescription = apiModel.place_description,
            placeRating = apiModel.place_rating,
            pictureName = apiModel.picture_name,
        )
    }
}
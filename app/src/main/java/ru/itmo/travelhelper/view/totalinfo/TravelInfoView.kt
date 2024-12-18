package ru.itmo.travelhelper.view.totalinfo

import ru.itmo.domain.models.totalinfo.TotalInfoPlaceModel

interface TravelInfoView {
    fun getTotalInfoPlaceData(totalInfoPlaceData: List<TotalInfoPlaceModel>)
}
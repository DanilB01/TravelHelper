package ru.itmo.travelhelper.view.main

import ru.itmo.domain.models.main.InterestingPlaceModel

interface MainExpressView {
    fun getInterestingPlaceData(interestingPlaceData: List<InterestingPlaceModel>)
}
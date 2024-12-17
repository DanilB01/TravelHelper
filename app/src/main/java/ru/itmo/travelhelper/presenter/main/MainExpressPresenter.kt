package ru.itmo.travelhelper.presenter.main

import ru.itmo.data.repositories.main.InterestingPlaceRepositoryImpl
import ru.itmo.domain.usecases.main.GetInterestingPlaceUseCase
import ru.itmo.travelhelper.view.main.MainExpressView


class MainExpressPresenter(private val view: MainExpressView) {
    private val getInterestingPlaceUseCase = GetInterestingPlaceUseCase(InterestingPlaceRepositoryImpl())


    fun loadInterestingPlaceData() {
        val interestingPlaceData = getInterestingPlaceUseCase.execute()

        view.getInterestingPlaceData(interestingPlaceData)
    }
}
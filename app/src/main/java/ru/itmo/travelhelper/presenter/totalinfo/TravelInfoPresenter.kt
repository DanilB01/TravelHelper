package ru.itmo.travelhelper.presenter.totalinfo

import ru.itmo.data.repositories.totalinfo.TotalInfoPlaceRepositoryImpl
import ru.itmo.domain.usecases.totalinfo.GetTotalInfoPlaceUseCase
import ru.itmo.travelhelper.view.totalinfo.TravelInfoView

class TravelInfoPresenter(private val view: TravelInfoView) {
    private val getTotalInfoPlaceUseCase = GetTotalInfoPlaceUseCase(
        TotalInfoPlaceRepositoryImpl()
    )

    fun loadInterestingPlaceData() {
        val totalInfoPlaceData = getTotalInfoPlaceUseCase.execute()

        view.getTotalInfoPlaceData(totalInfoPlaceData)
    }
}
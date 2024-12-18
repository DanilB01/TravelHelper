package ru.itmo.travelhelper.presenter.totalinfo

import ru.itmo.data.repositories.main.InterestingPlaceRepositoryImpl
import ru.itmo.domain.usecases.main.GetInterestingPlaceUseCase
import ru.itmo.travelhelper.view.totalinfo.TotalInfoView
import ru.itmo.travelhelper.view.totalinfo.TravelInfoView

class TotalInfoPresenter(private val view: TotalInfoView)
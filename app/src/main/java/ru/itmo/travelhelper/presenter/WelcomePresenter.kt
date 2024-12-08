package ru.itmo.travelhelper.presenter


import android.content.Context
import ru.itmo.data.prefs.LocalStorageImpl
import ru.itmo.travelhelper.view.InitView
import ru.itmo.domain.usecases.CompleteFirstLaunchUseCase

class WelcomePresenter(private val view: InitView) {

    fun setNextScreen(currentScreenInitNumber: Int) {
        view.showNextImageInit(currentScreenInitNumber)
        view.showNextTitleAndTextInit(currentScreenInitNumber)
        view.showNextButtonTextInit(currentScreenInitNumber)
    }

    fun completeFirstLaunch(context: Context) {
        val completeFirstLaunchUseCase = CompleteFirstLaunchUseCase(LocalStorageImpl(context))
        completeFirstLaunchUseCase.execute()
    }
}
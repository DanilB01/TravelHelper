package ru.itmo.travelhelper.presenter


import android.content.Context
import ru.itmo.data.prefs.LocalStorageImpl
import ru.itmo.domain.usecases.CompleteFirstLaunchUseCase
import ru.itmo.travelhelper.view.WelcomeView

class WelcomePresenter(private val view: WelcomeView) {

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
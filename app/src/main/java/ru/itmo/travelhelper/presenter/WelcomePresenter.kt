package ru.itmo.travelhelper.presenter

import ru.itmo.travelhelper.view.InitView

class WelcomePresenter (private val view: InitView) {
    fun setNextScreen(currentScreenInitNumber: Int) {
        view.showNextImageInit(currentScreenInitNumber)
        view.showNextTitleAndTextInit(currentScreenInitNumber)
        view.showNextButtonTextInit(currentScreenInitNumber)
    }
}
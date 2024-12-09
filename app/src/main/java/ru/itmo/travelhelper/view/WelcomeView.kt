package ru.itmo.travelhelper.view

interface WelcomeView {
    fun showNextTitleAndTextInit(initScreenNumber: Int)
    fun showNextImageInit(initScreenNumber: Int)
    fun showNextButtonTextInit(initScreenNumber: Int)
}
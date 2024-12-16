package ru.itmo.travelhelper.presenter


import android.content.Context
import ru.itmo.data.prefs.LocalStorageImpl
import ru.itmo.domain.usecases.CheckFirstLaunchUseCase
import ru.itmo.travelhelper.view.InitView

class InitPresenter(private val view: InitView) {
    fun isFirstLaunch(context: Context): Boolean {
        val isFirstLaunchUseCase = CheckFirstLaunchUseCase(LocalStorageImpl(context))
        return isFirstLaunchUseCase.execute()
    }
}
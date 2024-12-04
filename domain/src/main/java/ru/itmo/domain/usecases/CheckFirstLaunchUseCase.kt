package ru.itmo.domain.usecases


import ru.itmo.domain.repositories.LocalStorage


class CheckFirstLaunchUseCase(private val localStorage: LocalStorage) {
    fun execute() : Boolean{
        return localStorage.isFirstLaunch()
    }
}
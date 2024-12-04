package ru.itmo.domain.usecases


import ru.itmo.domain.repositories.LocalStorage


class CompleteFirstLaunchUseCase(private val localStorage: LocalStorage) {
    fun execute() {
        return localStorage.setFirstLaunchCompleted()
    }
}
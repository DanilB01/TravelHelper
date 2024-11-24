package ru.itmo.domain.usecases


import ru.itmo.domain.repositories.FirstLaunchCheckerRepository


class CheckFirstLaunchUseCase(private val firstLaunchCheckerRepository: FirstLaunchCheckerRepository) {
    fun execute() : Boolean{
        return firstLaunchCheckerRepository.isFirstLaunch()
    }
}
package ru.itmo.domain.usecases


import ru.itmo.domain.repositories.FirstLaunchCheckerRepository


class CompleteFirstLaunchUseCase(private val firstLaunchCheckerRepository: FirstLaunchCheckerRepository) {
    fun execute() {
        return firstLaunchCheckerRepository.setFirstLaunchCompleted()
    }
}
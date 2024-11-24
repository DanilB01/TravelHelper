package ru.itmo.domain.repositories


interface FirstLaunchCheckerRepository {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunchCompleted()
}

package ru.itmo.domain.repositories


interface LocalStorage {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunchCompleted()
}

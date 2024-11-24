package ru.itmo.data.prefs


import android.content.Context
import android.content.SharedPreferences
import ru.itmo.domain.repositories.FirstLaunchCheckerRepository


class FirstLaunchCheckerRepositoryImpl(context: Context) : FirstLaunchCheckerRepository {

    private val preferences: SharedPreferences =

        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override fun isFirstLaunch(): Boolean {
        return preferences.getBoolean("is_first_launch", true)
    }

    override fun setFirstLaunchCompleted() {
        preferences.edit().putBoolean("is_first_launch", false).apply()
    }
}
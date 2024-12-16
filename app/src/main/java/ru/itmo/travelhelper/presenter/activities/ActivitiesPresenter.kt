package ru.itmo.travelhelper.presenter.activities

import ru.itmo.travelhelper.view.activities.ActivitiesView

class ActivitiesPresenter(private val view: ActivitiesView) {

    fun onFragmentRequested(fragment: androidx.fragment.app.Fragment) {
        view.replaceFragment(fragment)
    }
}
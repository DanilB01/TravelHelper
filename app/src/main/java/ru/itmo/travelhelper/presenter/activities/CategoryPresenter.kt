package ru.itmo.travelhelper.presenter.activities

import ru.itmo.travelhelper.view.activities.CategoryView

class CategoryPresenter(private val view: CategoryView) {
    fun onNextButtonClicked(selectedCategories: List<String>) {
        view.navigateToDetails(selectedCategories.toTypedArray())
    }
}

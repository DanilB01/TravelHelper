package ru.itmo.travelhelper.presenter

import ru.itmo.travelhelper.view.CategoryView

class CategoryPresenter(private val view: CategoryView) {
    fun onNextButtonClicked(selectedCategories: List<String>) {
        view.navigateToDetails(selectedCategories.toTypedArray())
    }
}

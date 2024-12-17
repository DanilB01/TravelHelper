package ru.itmo.travelhelper.presenter.activities

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import ru.itmo.travelhelper.screens.activities.ActivitiesActivity
import ru.itmo.travelhelper.screens.totalinfo.TotalInfoActivity
import ru.itmo.travelhelper.view.activities.CategoryView

class CategoryPresenter(private val view: CategoryView) {
    fun onNextButtonClicked(selectedCategories: List<String>) {
        if (selectedCategories.isNotEmpty()) {
        view.navigateToDetails(selectedCategories.toTypedArray())
        }
        else {
            view.goToNextActivity()
        }
    }
}

package ru.itmo.travelhelper.presenter

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import ru.itmo.domain.usecases.GetDetailsUseCase
import ru.itmo.travelhelper.view.DetailsView

class DetailsPresenter(private val view: DetailsView) {

    fun onCategoriesLoaded(selectedCategories: Array<String>, useCase: GetDetailsUseCase) {
        for (category in selectedCategories) {
            val details = useCase.execute(category)
            val formattedText = createFormattedText(details.title, details.description)

            when (category) {
                "events" -> view.showEventsText(formattedText)
                "places" -> view.showPlacesText(formattedText)
                "food" -> view.showFoodText(formattedText)
            }
        }
    }

    private fun createFormattedText(title: String, description: String): SpannableString {
        val spannable = SpannableString("$title\n$description")
        spannable.setSpan(RelativeSizeSpan(1.5f), 0, title.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(RelativeSizeSpan(1f), title.length + 1, spannable.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}

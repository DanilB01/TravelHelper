package ru.itmo.travelhelper.presenter.activities

import ru.itmo.domain.models.activities.Event
import ru.itmo.domain.repositories.activities.EventsRepository
import ru.itmo.travelhelper.view.activities.EventsListView

class EventsListPresenter(
    private val view: EventsListView,
    private val repository: EventsRepository
) {
    private var eventsList: List<Event> = emptyList()

    fun loadEvents() {
        eventsList = repository.getEvents()
        view.showEvents(eventsList)
    }

    fun filterEvents(rating: Double?) {
        val filteredList = if (rating != null) {
            eventsList.filter { it.rating <= rating }
        } else {
            eventsList
        }
        view.showEvents(filteredList)
    }
}
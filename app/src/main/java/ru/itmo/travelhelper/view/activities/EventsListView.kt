package ru.itmo.travelhelper.view.activities

import ru.itmo.domain.models.activities.Event

interface EventsListView {
    fun showEvents(events: List<Event>)
}
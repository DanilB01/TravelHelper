package ru.itmo.domain.repositories.activities

import ru.itmo.domain.models.activities.Event

interface EventsRepository {
    fun getEvents(): List<Event>
}
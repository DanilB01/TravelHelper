package ru.itmo.domain.repositories.activities

import ru.itmo.domain.R
import ru.itmo.domain.models.activities.Event

class EventsRepositoryImpl : EventsRepository {
    override fun getEvents(): List<Event> {
        return listOf(
            Event("Собор Девы Марии", "Круглосуточно", 2.0, R.drawable.cathedral_photo),
            Event("Собор Девы Марии", "Круглосуточно", 2.0, R.drawable.cathedral_photo),
            Event("Остров Джейси", "Круглосуточно", 5.0, R.drawable.jacey_island_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo),
            Event("Benalla Tourist Park", "Открыто с 8.00 до 18.00", 4.5, R.drawable.benalla_park_photo)
        )
    }
}
package ru.itmo.domain.repositories.activities

import ru.itmo.domain.R
import ru.itmo.domain.models.activities.Event

class PlacesRepositoryImpl : EventsRepository {
    override fun getEvents(): List<Event> {
        return listOf(
            Event("ATTY Gallery", "Круглосуточно", 2.0, R.drawable.atty_gallery),
            Event("Carriageworks", "Круглосуточно", 4.5, R.drawable.carriageworks),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),
            Event("Luna Park", "Круглосуточно", 5.0, R.drawable.luna_park),

        )
    }
}
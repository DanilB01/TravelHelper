package ru.itmo.domain.repositories.activities

import ru.itmo.domain.R
import ru.itmo.domain.models.activities.Event

class FoodRepositoryImpl : EventsRepository {
    override fun getEvents(): List<Event> {
        return listOf(
            Event("FISHBOWL", "Круглосуточно", 4.5, R.drawable.fishbowl),
            Event("Pizza Boccone", "Круглосуточно", 2.0, R.drawable.pizza_boccone),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),
            Event("Bistro Papillon", "Круглосуточно", 5.0, R.drawable.bistro_papillon),

        )
    }
}
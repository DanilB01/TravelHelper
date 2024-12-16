package ru.itmo.domain.repositories

class CategoryRepository {
    fun getCategories(): List<String> {
        return listOf("events", "places", "food")
    }
}

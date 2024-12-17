package ru.itmo.domain.repositories.activities

class CategoryRepositoryImpl : CategoryRepository {
    override fun getCategories(): List<String> {
        return listOf("events", "places", "food")
    }
}
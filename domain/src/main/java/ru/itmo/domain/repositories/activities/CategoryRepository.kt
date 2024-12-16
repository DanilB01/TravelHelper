package ru.itmo.domain.repositories.activities

interface CategoryRepository {
    fun getCategories(): List<String>
}

package ru.itmo.domain.usecases.activities

import ru.itmo.domain.repositories.activities.CategoryRepository

class GetCategoriesUseCase(private val repository: CategoryRepository) {
    fun execute(): List<String> {
        return repository.getCategories()
    }
}

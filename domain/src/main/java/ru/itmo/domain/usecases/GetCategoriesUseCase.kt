package ru.itmo.domain.usecases

import ru.itmo.domain.repositories.CategoryRepository

class GetCategoriesUseCase(private val repository: CategoryRepository) {
    fun execute(): List<String> {
        return repository.getCategories()
    }
}

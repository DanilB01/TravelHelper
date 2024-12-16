package ru.itmo.domain.usecases.activities

class GetDetailsUseCase {

    data class CategoryDetails(val title: String, val description: String)

    fun execute(category: String): CategoryDetails {
        return when (category) {
            "events" -> CategoryDetails(
                "Мероприятия",
                "Различные события/активности, например, концерты, выставки, фестивали"
            )
            "places" -> CategoryDetails(
                "Места",
                "Знакомые локации, исторические объекты, достопримечательности, природные зоны"
            )
            "food" -> CategoryDetails(
                "Еда",
                "Кафе, рестораны и другие места, где можно насладиться разнообразными блюдами"
            )
            else -> CategoryDetails("Неизвестно", "Нет данных для выбранной категории")
        }
    }
}

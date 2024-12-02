package ru.itmo.data.repositories.flight


import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.domain.repositories.flight.LocationRepository

class LocationRepositoryImpl: LocationRepository {
    // TODO: Add service implementation

    private val country_data = arrayOf("Россия", "США", "Канада", "Китай", "Индия", "Германия", "Франция", "Великобритания",
        "Италия", "Испания", "Австралия", "Бразилия", "Япония", "Мексика", "Турция")

    private val cities_data = mapOf(
        "Россия" to arrayOf("Москва", "Санкт-Петербург", "Новосибирск"),
        "США" to arrayOf("Нью-Йорк", "Лос-Анджелес", "Чикаго"),
        "Канада" to arrayOf("Торонто", "Монреаль", "Ванкувер"),
        "Китай" to arrayOf("Пекин", "Шанхай", "Гуанчжоу"),
        "Индия" to arrayOf("Дели", "Мумбаи", "Колката"),
        "Германия" to arrayOf("Берлин", "Мюнхен", "Гамбург"),
        "Франция" to arrayOf("Париж", "Марсель", "Лион"),
        "Великобритания" to arrayOf("Лондон", "Манчестер", "Бирмингем"),
        "Италия" to arrayOf("Рим", "Милан", "Неаполь"),
        "Испания" to arrayOf("Мадрид", "Барселона", "Севилья"),
        "Австралия" to arrayOf("Сидней", "Мельбурн", "Брисбен"),
        "Бразилия" to arrayOf("Сан-Паулу", "Рио-де-Жанейро", "Салвадор"),
        "Япония" to arrayOf("Токио", "Осака", "Йокогама"),
        "Мексика" to arrayOf("Мехико", "Гуадалахара", "Мерида"),
        "Турция" to arrayOf("Стамбул", "Анкара", "Измир")
    )

    val airports_data = mapOf(
        "Москва" to listOf("Шереметьево", "Домодедово", "Внуково"), "Санкт-Петербург" to listOf("Пулково"), "Новосибирск" to listOf("Толмачево"),
        "Нью-Йорк" to listOf("Джон Ф. Кеннеди", "Ла-Гуардиа", "Ньюарк Либерти"), "Лос-Анджелес" to listOf("Международный аэропорт Лос-Анджелеса"), "Чикаго" to listOf("О'Хара", "Мидуэй"),
        "Торонто" to listOf("Пирсон", "Билли Бишоп"), "Монреаль" to listOf("Монреаль-Трюдо"), "Ванкувер" to listOf("Международный аэропорт Ванкувера"),
        "Пекин" to listOf("Столичный международный аэропорт Пекина", "Дасин"), "Шанхай" to listOf("Пудун", "Хунцяо"), "Гуанчжоу" to listOf("Байюнь"),
        "Дели" to listOf("Индира Ганди"), "Мумбаи" to listOf("Чхатрапати Шиваджи Махарадж"), "Колката" to listOf("Нетаджи Субхаш Чандра Бос"),
        "Берлин" to listOf("Тегель", "Шёнефельд", "Бранденбург"), "Мюнхен" to listOf("Франца-Йозефа Штрауса"), "Гамбург" to listOf("Гамбург-Фульсбюттель"),
        "Париж" to listOf("Шарль де Голль", "Орли"), "Марсель" to listOf("Марсель Прованс"), "Лион" to listOf("Сен-Экзюпери"),
        "Лондон" to listOf("Хитроу", "Гэтвик", "Станстед", "Лондон-Сити"), "Манчестер" to listOf("Манчестер"), "Бирмингем" to listOf("Бирмингем"),
        "Рим" to listOf("Леонардо да Винчи – Фьюмичино", "Чампино"), "Милан" to listOf("Мальпенса", "Линате"), "Неаполь" to listOf("Каподичино"),
        "Мадрид" to listOf("Барахас"), "Барселона" to listOf("Эль Прат"), "Севилья" to listOf("Сан-Пабло"),
        "Сидней" to listOf("Королевский Сидней"), "Мельбурн" to listOf("Тулламарин"), "Брисбен" to listOf("Брисбен"),
        "Сан-Паулу" to listOf("Гуарульюс", "Конгоньяс"), "Рио-де-Жанейро" to listOf("Галеан"), "Салвадор" to listOf("Депутат Луис Эдуарду Магальяйнс"),
        "Токио" to listOf("Нарита", "Ханэда"), "Осака" to listOf("Кансай", "Итами"), "Йокогама" to listOf("Ханэда"), // Аэропорт Ханэда обслуживает и Токио, и Йокогаму
        "Мехико" to listOf("Мехико-Сити"), "Гуадалахара" to listOf("Дон Мигель Идальго-и-Костилья"), "Мерида" to listOf("Мануэль Крессенсио Рехон"),
        "Стамбул" to listOf("Ататюрк", "Сабиха Гёкчен", "Новый Стамбульский аэропорт"), "Анкара" to listOf("Эсенбога"), "Измир" to listOf("Аднан Мендерес")
    )

    override suspend fun getCountries(): List<CountryModel> {
        // TODO("add getting countries from service")
        return country_data.map { CountryModel(it) }

    }

    override suspend fun getCities(): Map<CountryModel, List<CityModel>> {
        // TODO("add getting cities from service")
        return cities_data
            .mapValues { entry -> entry.value.map { city -> CityModel(city) } }
            .mapKeys { entry -> CountryModel(entry.key) }
    }

    override suspend fun getAirports(): Map<CityModel, List<AirportModel>> {
        // TODO("add getting airports from service")
        return airports_data
            .mapValues { entry -> entry.value.map { airport -> AirportModel(airport) } }
            .mapKeys { entry -> CityModel(entry.key) }
    }


}
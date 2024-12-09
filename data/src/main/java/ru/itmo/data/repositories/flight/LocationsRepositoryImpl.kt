package ru.itmo.data.repositories.flight


import ru.itmo.data.mappers.HotelMapper
import ru.itmo.data.mappers.flight.AirportMapper
import ru.itmo.data.mappers.flight.CityMapper
import ru.itmo.data.mappers.flight.CountryMapper
import ru.itmo.data.remote.flight.AirportApiModel
import ru.itmo.data.remote.flight.CityApiModel
import ru.itmo.data.remote.flight.CountryApiModel
import ru.itmo.domain.models.flight.AirportModel
import ru.itmo.domain.models.flight.CityModel
import ru.itmo.domain.models.flight.CountryModel
import ru.itmo.domain.repositories.flight.LocationRepository

class LocationsRepositoryImpl: LocationRepository {
    private val mapperCountry = CountryMapper()
    private val mapperCity = CityMapper()
    private val mapperAirport = AirportMapper()

    // TODO: Add service implementation

    private val country_data = listOf(
        CountryApiModel("Россия"), CountryApiModel("США"), CountryApiModel("Канада"), CountryApiModel("Китай"),
        CountryApiModel("Индия"), CountryApiModel("Германия"), CountryApiModel("Франция"), CountryApiModel("Великобритания"),
        CountryApiModel("Италия"), CountryApiModel("Испания"), CountryApiModel("Австралия"),
        CountryApiModel("Бразилия"), CountryApiModel("Япония"), CountryApiModel("Мексика"), CountryApiModel("Турция"))

    private val cities_data = mapOf(
        CountryApiModel("Россия") to listOf(CityApiModel("Москва"), CityApiModel("Санкт-Петербург"), CityApiModel("Новосибирск")),
        CountryApiModel("США") to listOf(CityApiModel("Нью-Йорк"), CityApiModel("Лос-Анджелес"), CityApiModel("Чикаго")),
        CountryApiModel("Китай") to listOf(CityApiModel("Пекин"), CityApiModel("Шанхай"), CityApiModel("Гуанчжоу")),
        CountryApiModel("Канада") to listOf(CityApiModel("Торонто"), CityApiModel("Монреаль"), CityApiModel("Ванкувер")),
        CountryApiModel("Индия") to listOf(CityApiModel("Дели"), CityApiModel("Мумбаи"), CityApiModel("Колката")),
        CountryApiModel("Германия") to listOf(CityApiModel("Берлин"), CityApiModel("Мюнхен"), CityApiModel("Гамбург")),
        CountryApiModel("Франция") to listOf(CityApiModel("Париж"), CityApiModel("Марсель"), CityApiModel("Лион")),
        CountryApiModel("Великобритания") to listOf(CityApiModel("Лондон"), CityApiModel("Манчестер"), CityApiModel("Бирмингем")),
        CountryApiModel("Италия") to listOf(CityApiModel("Рим"), CityApiModel("Милан"), CityApiModel("Неаполь")),
        CountryApiModel("Испания") to listOf(CityApiModel("Мадрид"), CityApiModel("Барселона"), CityApiModel("Севилья")),
        CountryApiModel("Австралия") to listOf(CityApiModel("Сидней"), CityApiModel("Мельбурн"), CityApiModel("Брисбен")),
        CountryApiModel("Бразилия") to listOf(CityApiModel("Сан-Паулу"), CityApiModel("Рио-де-Жанейро"), CityApiModel("Салвадор")),
        CountryApiModel("Япония") to listOf(CityApiModel("Токио"), CityApiModel("Осака"), CityApiModel("Йокогама")),
        CountryApiModel("Мексика") to listOf(CityApiModel("Мехико"), CityApiModel("Гуадалахара"), CityApiModel("Мерида")),
        CountryApiModel("Турция") to listOf(CityApiModel("Стамбул"), CityApiModel("Анкара"), CityApiModel("Измир"))
    )

    val airports_data = mapOf(
        CityApiModel("Москва") to listOf(AirportApiModel("Шереметьево"), AirportApiModel("Домодедово"), AirportApiModel("Внуково")), CityApiModel("Санкт-Петербург") to listOf(AirportApiModel("Пулково")), CityApiModel("Новосибирск") to listOf(AirportApiModel("Толмачево")),
        CityApiModel("Нью-Йорк") to listOf(AirportApiModel("Джон Ф. Кеннеди"), AirportApiModel("Ла-Гуардиа"), AirportApiModel("Ньюарк Либерти")), CityApiModel("Лос-Анджелес") to listOf(AirportApiModel("Международный аэропорт Лос-Анджелеса")), CityApiModel("Чикаго") to listOf(AirportApiModel("О'Хара"), AirportApiModel("Мидуэй")),
        CityApiModel("Торонто") to listOf(AirportApiModel("Пирсон"), AirportApiModel("Билли Бишоп")), CityApiModel("Монреаль") to listOf(AirportApiModel("Монреаль-Трюдо")), CityApiModel("Ванкувер") to listOf(AirportApiModel("Международный аэропорт Ванкувера")),
        CityApiModel("Пекин") to listOf(AirportApiModel("Столичный международный аэропорт Пекина"), AirportApiModel("Дасин")), CityApiModel("Шанхай") to listOf(AirportApiModel("Пудун"), AirportApiModel("Хунцяо")), CityApiModel("Гуанчжоу") to listOf(AirportApiModel("Байюнь")),
        CityApiModel("Дели") to listOf(AirportApiModel("Индира Ганди")), CityApiModel("Мумбаи") to listOf(AirportApiModel("Чхатрапати Шиваджи Махарадж")), CityApiModel("Колката") to listOf(AirportApiModel("Нетаджи Субхаш Чандра Бос")),
        CityApiModel("Берлин") to listOf(AirportApiModel("Тегель"), AirportApiModel("Шёнефельд"), AirportApiModel("Бранденбург")), CityApiModel("Мюнхен") to listOf(AirportApiModel("Франца-Йозефа Штрауса")), CityApiModel("Гамбург") to listOf(AirportApiModel("Гамбург-Фульсбюттель")),
        CityApiModel("Париж") to listOf(AirportApiModel("Шарль де Голль"), AirportApiModel("Орли")), CityApiModel("Марсель") to listOf(AirportApiModel("Марсель Прованс")), CityApiModel("Лион") to listOf(AirportApiModel("Сен-Экзюпери")),
        CityApiModel("Лондон") to listOf(AirportApiModel("Хитроу"), AirportApiModel("Гэтвик"), AirportApiModel("Станстед"), AirportApiModel("Лондон-Сити")), CityApiModel("Манчестер") to listOf(AirportApiModel("Манчестер")), CityApiModel("Бирмингем") to listOf(AirportApiModel("Бирмингем")),
        CityApiModel("Рим") to listOf(AirportApiModel("Леонардо да Винчи – Фьюмичино"), AirportApiModel("Чампино")), CityApiModel("Милан") to listOf(AirportApiModel("Мальпенса"), AirportApiModel("Линате")), CityApiModel("Неаполь") to listOf(AirportApiModel("Каподичино")),
        CityApiModel("Мадрид") to listOf(AirportApiModel("Барахас")), CityApiModel("Барселона") to listOf(AirportApiModel("Эль Прат")), CityApiModel("Севилья") to listOf(AirportApiModel("Сан-Пабло")),
        CityApiModel("Сидней") to listOf(AirportApiModel("Королевский Сидней")), CityApiModel("Мельбурн") to listOf(AirportApiModel("Тулламарин")), CityApiModel("Брисбен") to listOf(AirportApiModel("Брисбен")),
        CityApiModel("Сан-Паулу") to listOf(AirportApiModel("Гуарульюс"), AirportApiModel("Конгоньяс")), CityApiModel("Рио-де-Жанейро") to listOf(AirportApiModel("Галеан")), CityApiModel("Салвадор") to listOf(AirportApiModel("Депутат Луис Эдуарду Магальяйнс")),
        CityApiModel("Токио") to listOf(AirportApiModel("Нарита"), AirportApiModel("Ханэда")), CityApiModel("Осака") to listOf(AirportApiModel("Кансай"), AirportApiModel("Итами")), CityApiModel("Йокогама") to listOf(AirportApiModel("Ханэда")), // Аэропорт Ханэда обслуживает и Токио, и Йокогаму
        CityApiModel("Мехико") to listOf(AirportApiModel("Мехико-Сити")), CityApiModel("Гуадалахара") to listOf(AirportApiModel("Дон Мигель Идальго-и-Костилья")), CityApiModel("Мерида") to listOf(AirportApiModel("Мануэль Крессенсио Рехон")),
        CityApiModel("Стамбул") to listOf(AirportApiModel("Ататюрк"), AirportApiModel("Сабиха Гёкчен"), AirportApiModel("Новый Стамбульский аэропорт")), CityApiModel("Анкара") to listOf(AirportApiModel("Эсенбога")), CityApiModel("Измир") to listOf(AirportApiModel("Аднан Мендерес"))
    )

    override suspend fun getCountries(): List<CountryModel> {
        // TODO("add getting countries from service")
        return country_data.map { apiModelCountry -> mapperCountry.mapFromApiModel(apiModelCountry) }
    }

    override suspend fun getCities(): Map<CountryModel, List<CityModel>> {
        // TODO("add getting cities from service")
        return cities_data
            .mapValues { entry -> entry.value.map { apiModelCity -> mapperCity.mapFromApiModel(apiModelCity) } }
            .mapKeys { apiModelCountry -> mapperCountry.mapFromApiModel(apiModelCountry.key) }
    }

    override suspend fun getAirports(): Map<CityModel, List<AirportModel>> {
        // TODO("add getting airports from service")
        return airports_data
            .mapValues { entry -> entry.value.map { apiModelAirport -> mapperAirport.mapFromApiModel(apiModelAirport) } }
            .mapKeys { apiModelCity -> mapperCity.mapFromApiModel(apiModelCity.key) }
    }


}
package ru.itmo.data.remote.event

data class EventResponse(
    val _links: Links,
    val _embedded: Embedded,
    val page: Page
)

data class Links(
    val self: Link,
    val next: Link
)

data class Link(
    val href: String,
    val templated: Boolean
)

data class Embedded(
    val events: List<Event>
)

data class Event(
    val name: String,
    val type: String,
    val id: String,
    val test: Boolean,
    val url: String,
    val locale: String,
    val images: List<Image>,
    val sales: Sales,
    val dates: Dates,
    val classifications: List<Classification>,
    val promoter: Promoter?,
    val _links: EventLinks,
    val _embedded: EventEmbedded?
)

data class Image(
    val ratio: String,
    val url: String,
    val width: Int,
    val height: Int,
    val fallback: Boolean
)

data class Sales(
    val public: PublicSale
)

data class PublicSale(
    val startDateTime: String,
    val startTBD: Boolean,
    val endDateTime: String
)

data class Dates(
    val start: StartDate,
    val timezone: String?,
    val status: Status
)

data class StartDate(
    val localDate: String,
    val dateTBD: Boolean,
    val dateTBA: Boolean,
    val timeTBA: Boolean,
    val noSpecificTime: Boolean
)

data class Status(
    val code: String
)

data class Classification(
    val primary: Boolean,
    val segment: Segment,
    val genre: Genre,
    val subGenre: SubGenre
)

data class Segment(val id: String, val name: String)
data class Genre(val id: String, val name: String)
data class SubGenre(val id: String, val name: String)

data class Promoter(val id: String)
data class EventLinks(val self: Link, val attractions: List<Link>?, val venues: List<Link>?)
data class EventEmbedded(val venues: List<Venue>?, val attractions: List<Attraction>?)

data class Venue(
    val name: String,
    val type: String,
    val id: String,
    val test: Boolean,
    val locale: String,
    val postalCode: String?,
    val timezone: String?,
    val city: City,
    val state: State,
    val country: Country,
    val address: Address,
    val location: Location,
    val markets: List<Market>,
    val _links: Links
)

data class City(val name: String)
data class State(val name: String, val stateCode: String)
data class Country(val name: String, val countryCode: String)
data class Address(val line1: String)
data class Location(val longitude: String, val latitude: String)
data class Market(val id: String)

data class Attraction(
    val name: String,
    val type: String,
    val id: String,
    val test: Boolean,
    val locale: String,
    val images: List<Image>,
    val classifications: List<Classification>,
    val _links: Link
)

data class Page(val size: Int, val totalElements: Int, val totalPages: Int, val number: Int)


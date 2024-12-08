package ru.itmo.data.remote.restaurant

data class RestaurantResponse(
    val results: List<RestaurantApiModel>,
    val context: Context
)

data class RestaurantApiModel(
    val fsq_id: String,
    val categories: List<Category>,
    val chains: List<Chains>,
    val closed_bucket: String,
    val date_closed: String,
    val description: String,
    val distance: Int,
    val email: String,
    val fax: String,
    val features: Features,
    val geocodes: Geocodes,
    val hours: Hours?,
    val hours_popular: List<Hour>?,
    val link: String?,
    val location: Location,
    val menu: String?,
    val name: String?,
    val photos: List<Photo>?,
    val popularity: Int?,
    val price: Int?,
    val rating: Int?,
    val related_places: RelatedPlaces?,
    val social_media: SocialMedia?,
    val stats: Stats?,
    val store_id: String?,
    val tastes: List<String>?,
    val tel: String?,
    val timezone: String?,
    val tips: List<Tip>?,
    val venue_reality_bucket: String?,
    val verified: Boolean?,
    val website: String?
)

data class RelatedPlaces(
    val parent: ParentPlace? // Обновляем тип на объект, а не строку
)

data class ParentPlace(
    val id: String?,
    val name: String?
)


data class Chains(
    val id: String,
    val name: String
)

data class Category(
    val id: Int,
    val name: String,
    val short_name: String,
    val plural_name: String,
    val icon: Icon
)

data class Icon(
    val id: String,
    val created_at: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int,
    val classifications: List<String>,
    val tip: Tip
)

data class Location(
    val address: String?,
    val address_extended: String?,
    val admin_region: String?,
    val census_block: String?,
    val country: String?,
    val cross_street: String?,
    val dma: String?,
    val formatted_address: String?,
    val locality: String?,
    val neighborhood: List<String>?,
    val po_box: String?,
    val post_town: String?,
    val postcode: String?,
    val region: String?
)

data class Geocodes(
    val drop_off: Coordinate?,
    val front_door: Coordinate?,
    val main: Coordinate?,
    val road: Coordinate?,
    val roof: Coordinate?
)

data class Coordinate(
    val latitude: Double,
    val longitude: Double
)

data class Features(
    val payment: Payment?,
    val food_and_drink: FoodAndDrink?,
    val services: Services?,
    val amenities: Amenities?,
    val attributes: Attributes?
)

data class Payment(
    val credit_cards: CreditCards?,
    val digital_wallet: DigitalWallet?
)

data class CreditCards(
    val accepts_credit_cards: Any?,
    val amex: Any?,
    val discover: Any?,
    val visa: Any?,
    val diners_club: Any?,
    val master_card: Any?,
    val union_pay: Any?
)

data class DigitalWallet(
    val accepts_nfc: Any?
)

data class FoodAndDrink(
    val alcohol: Alcohol?,
    val meals: Meals?
)

data class Alcohol(
    val bar_service: Any?,
    val beer: Any?,
    val byo: Any?,
    val cocktails: Any?,
    val full_bar: Any?,
    val wine: Any?
)

data class Meals(
    val bar_snacks: Any?,
    val breakfast: Any?,
    val brunch: Any?,
    val lunch: Any?,
    val happy_hour: Any?,
    val dessert: Any?,
    val dinner: Any?,
    val tasting_menu: Any?
)

data class Services(
    val delivery: Any?,
    val takeout: Any?,
    val drive_through: Any?,
    val dine_in: DineIn?
)

data class DineIn(
    val reservations: Any?,
    val online_reservations: Any?,
    val groups_only_reservations: Any?,
    val essential_reservations: Any?
)

data class Amenities(
    val restroom: Any?,
    val smoking: Any?,
    val jukebox: Any?,
    val music: Any?,
    val live_music: Any?,
    val private_room: Any?,
    val outdoor_seating: Any?,
    val tvs: Any?,
    val atm: Any?,
    val coat_check: Any?,
    val wheelchair_accessible: Any?,
    val parking: Parking?,
    val sit_down_dining: Any?,
    val wifi: String?
)

data class Parking(
    val parking: Any?,
    val street_parking: Any?,
    val valet_parking: Any?,
    val public_lot: Any?,
    val private_lot: Any?
)

data class Attributes(
    val business_meeting: String?,
    val clean: String?,
    val crowded: String?,
    val dates_popular: String?,
    val dressy: String?,
    val families_popular: String?,
    val gluten_free_diet: String?,
    val good_for_dogs: String?,
    val groups_popular: String?,
    val healthy_diet: String?,
    val late_night: String?,
    val noisy: String?,
    val quick_bite: String?,
    val romantic: String?,
    val service_quality: String?,
    val singles_popular: String?,
    val special_occasion: String?,
    val trendy: String?,
    val value_for_money: String?,
    val vegan_diet: String?,
    val vegetarian_diet: String?
)

data class Photo(
    val id: String,
    val created_at: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int,
    val classifications: List<String>,
    val tip: Tip
)


data class Stats(
    val total_photos: Int?,
    val total_ratings: Int?,
    val total_tips: Int?
)

data class SocialMedia(
    val facebook_id: String?,
    val instagram: String?,
    val twitter: String?
)

data class Hour(
    val close: String?,
    val day: Int?,
    val open: String?
)

data class Hours(
    val display: String?,
    val is_local_holiday: Boolean?,
    val open_now: Boolean?,
    val regular: List<Hour>?
)

data class Context(
    val geo_bounds: GeoBounds
)

data class GeoBounds(
    val circle: Circle
)

data class Circle(
    val center: Coordinate,
    val radius: Int
)

data class PhotoResponse(
    val id: String,
    val created_at: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int,
    val classifications: List<String>,
    val tip: Tip
)

data class Tip(
    val id: String,
    val created_at: String,
    val text: String,
    val url: String,
    val lang: String,
    val agree_count: Int,
    val disagree_count: Int
)

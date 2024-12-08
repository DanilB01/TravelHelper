package ru.itmo.data.remote.hotel

data class HotelResponse(
    val data: List<HotelApiModel>,
    val meta: Meta
)

data class HotelApiModel(
    val chainCode: String,
    val iataCode: String,
    val dupeId: Long,
    val name: String,
    val hotelId: String,
    val geoCode: GeoCode,
    val address: Address,
    val distance: Distance
)

data class GeoCode(
    val latitude: Double,
    val longitude: Double
)

data class Address(
    val countryCode: String
)

data class Distance(
    val value: Double,
    val unit: String
)

data class Meta(
    val count: Int,
    val links: Links
)

data class Links(
    val self: String
)

package ru.itmo.data.remote.hotel

data class HotelSearchResponse(
    val data: List<HotelSearchData>
)

data class HotelSearchData(
    val type: String,
    val hotel: Hotel,
    val offers: List<HotelOffer>
)

data class Hotel(
    val hotelIds: String,
    val name: String
)

data class HotelOffer(
    val price: Price
)

data class Price(
    val currency: String,
    val total: String
)


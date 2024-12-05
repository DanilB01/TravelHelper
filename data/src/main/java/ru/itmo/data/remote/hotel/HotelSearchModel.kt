data class HotelSearchResponse(
    val data: List<HotelSearchModel>
)

data class HotelSearchModel(
    val type: String,
    val hotel: HotelDetails,
    val available: Boolean,
    val offers: List<HotelOffer>?,
    val self: String
)

data class HotelDetails(
    val type: String,
    val hotelId: String,
    val chainCode: String,
    val dupeId: String,
    val name: String,
    val cityCode: String,
    val latitude: Double,
    val longitude: Double
)

data class HotelOffer(
    val id: String,
    val checkInDate: String,
    val checkOutDate: String,
    val rateCode: String,
    val rateFamilyEstimated: RateFamily?,
    val room: RoomDetails?,
    val guests: GuestsInfo,
    val price: PriceDetails,
    val policies: Policies?,
    val self: String
)

data class RateFamily(
    val code: String,
    val type: String
)

data class RoomDetails(
    val type: String,
    val typeEstimated: RoomTypeEstimated?,
    val description: RoomDescription?
)

data class RoomTypeEstimated(
    val category: String,
    val beds: Int?,
    val bedType: String?
)

data class RoomDescription(
    val text: String,
    val lang: String
)

data class GuestsInfo(
    val adults: Int
)

data class PriceDetails(
    val currency: String,
    val base: String,
    val total: String,
    val variations: PriceVariations?
)

data class PriceVariations(
    val average: AveragePrice?,
    val changes: List<PriceChange>?
)

data class AveragePrice(
    val base: String
)

data class PriceChange(
    val startDate: String,
    val endDate: String,
    val total: String
)

data class Policies(
    val paymentType: String?,
    val cancellation: CancellationPolicy?
)

data class CancellationPolicy(
    val description: CancellationDescription?,
    val type: String?
)

data class CancellationDescription(
    val text: String
)

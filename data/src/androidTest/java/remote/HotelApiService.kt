package remote

interface HotelApiService {
    @GET("hotels")
    suspend fun getHotels(): List<remote.HotelApiModel>

}
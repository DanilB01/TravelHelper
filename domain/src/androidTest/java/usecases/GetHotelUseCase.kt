package usecases

class GetHotelsUseCase(private val hotelRepository: repositories.HotelRepository) {
    suspend fun execute(): List<models.Hotel> {
        return hotelRepository.getHotels()
    }
}
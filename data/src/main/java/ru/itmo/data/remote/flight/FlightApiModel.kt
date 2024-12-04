data class FlightsResponse(
    val data: List<FlightApiModel>
)

data class FlightApiModel(
    val flight: Flight,
    val airline: Airline,
    val departure: Departure,
    val arrival: Arrival
)

data class Flight(
    val iata: String,
    val number: String
)

data class Airline(
    val name: String
)

data class Departure(
    val airport: String,
    val iata: String,
    val scheduled: String
)

data class Arrival( // модель прибытия
    val airport: String,
    val iata: String,
    val scheduled: String
)



package local.user_travel.ticket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class TicketsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pointOfDeparture: String,
    val destination: String,
    val ticketPrice: String,
    val flightTime: String,
    val typeOfClassOfService: String
)

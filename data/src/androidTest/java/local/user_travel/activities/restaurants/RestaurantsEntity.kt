package local.user_travel.activities.restaurants

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val restaurantName: String,
    val location: String,
    val openingHours: String
)

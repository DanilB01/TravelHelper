package local.user_travel.activities.events


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val eventName: String,
    val eventDate: String,
    val location: String
)

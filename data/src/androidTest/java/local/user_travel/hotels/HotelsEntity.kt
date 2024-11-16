package local.user_travel.hotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class HotelsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val stars: Int,
    val address: String,
    val periodOfStay: String
)

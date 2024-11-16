package ru.itmo.data.local.user_travel.activities.landmarks


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landmarks")
data class LandmarksEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val landmarkName: String,
    val location: String
)

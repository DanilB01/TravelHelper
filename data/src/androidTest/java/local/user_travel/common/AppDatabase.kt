package local.user_travel.common

import androidx.room.Database
import androidx.room.RoomDatabase
import local.user_travel.activities.events.EventsDAO
import local.user_travel.activities.events.EventsEntity
import local.user_travel.activities.landmarks.LandmarksDAO
import local.user_travel.activities.landmarks.LandmarksEntity
import local.user_travel.activities.restaurants.RestaurantsDAO
import local.user_travel.activities.restaurants.RestaurantsEntity
import local.user_travel.hotels.HotelsDAO
import local.user_travel.hotels.HotelsEntity
import local.user_travel.tickets.TicketsDAO
import local.user_travel.tickets.TicketsEntity

@Database(
    entities = [
        EventsEntity::class,
        LandmarksEntity::class,
        RestaurantsEntity::class,
        HotelsEntity::class,
        TicketsEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getEventsDAO(): EventsDAO
    abstract fun getLandmarksDAO(): LandmarksDAO
    abstract fun getRestaurantsDAO(): RestaurantsDAO
    abstract fun getHotelsDAO(): HotelsDAO
    abstract fun getTicketsDAO(): TicketsDAO
}
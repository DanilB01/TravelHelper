package local.user_travel.common

import android.content.Context
import androidx.room.Room

object Database {
    private lateinit var database: AppDatabase
    fun initDatabase(context: Context) {
        if (::database.isInitialized) return
        database = build(context)
    }

    fun getEventsDAO() = database.getEventsDAO()
    fun getLandmarksDAO() = database.getLandmarksDAO()
    fun getRestaurantsDAO() = database.getRestaurantsDAO()
    fun getHotelsDAO() = database.getHotelsDAO()
    fun getTicketsDAO() = database.getTicketsDAO()

    private fun build(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "travel_app_database"
    ).build()
}
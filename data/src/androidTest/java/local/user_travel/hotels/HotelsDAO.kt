package local.user_travel.hotels

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HotelsDAO {
    @Query("SELECT * FROM hotels")
    suspend fun getHotels(): List<HotelsEntity>?

    /*
        Чтобы сделать вывод одной строки, нужно в каком-нибудь SharedPreferences сделать отслеживание
        по тому, какой это добавленный маршрут по счёту, чтобы из всех таблиц в БД выводить только те строки,
        которые соответствуют номеру маршрута
        @Query("SELECT * FROM events WHERE travel_number = :travelNumber")
        suspend fun getHotelById(id: Int): HotelsEntity?
    */
    @Insert
    suspend fun addHotel(hotel: HotelsEntity)

    @Update
    suspend fun updateHotel(hotel: HotelsEntity)

    /*
        @Query("UPDATE events SET status = :status WHERE id = :id")
        suspend fun markTaskDone(id: Int, status: String)

    */

    /* То же самое замечание, что и сверху (если удаление вообще нужно)
        @Query("DELETE FROM events WHERE id = :id")
        suspend fun deleteHotelById(id: Int)

    */
}
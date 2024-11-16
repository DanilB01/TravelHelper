package ru.itmo.data.local.user_travel.activities.landmarks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LandmarksDAO {
    @Query("SELECT * FROM landmarks")
    suspend fun getLandmarks(): List<LandmarksEntity>?

    /*
        Чтобы сделать вывод одной строки, нужно в каком-нибудь SharedPreferences сделать отслеживание
        по тому, какой это добавленный маршрут по счёту, чтобы из всех таблиц в БД выводить только те строки,
        которые соответствуют номеру маршрута
        @Query("SELECT * FROM events WHERE travel_number = :travelNumber")
        suspend fun getLandmarkById(id: Int): LandmarksEntity?
    */
    @Insert
    suspend fun addLandmark(landmark: LandmarksEntity)

    @Update
    suspend fun updateLandmark(landmark: LandmarksEntity)

    /*
        @Query("UPDATE events SET status = :status WHERE id = :id")
        suspend fun markTaskDone(id: Int, status: String)

    */

    /* То же самое замечание, что и сверху (если удаление вообще нужно)
        @Query("DELETE FROM events WHERE id = :id")
        suspend fun deleteLandmarkById(id: Int)

    */
}
package local.user_travel.tickets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TicketsDAO {
    @Query("SELECT * FROM tickets")
    suspend fun getTickets(): List<TicketsEntity>?

    /*
        Чтобы сделать вывод одной строки, нужно в каком-нибудь SharedPreferences сделать отслеживание
        по тому, какой это добавленный маршрут по счёту, чтобы из всех таблиц в БД выводить только те строки,
        которые соответствуют номеру маршрута
        @Query("SELECT * FROM events WHERE travel_number = :travelNumber")
        suspend fun getTicketById(id: Int): TicketsEntity?
    */
    @Insert
    suspend fun addTicket(ticket: TicketsEntity)

    @Update
    suspend fun updateTicket(ticket: TicketsEntity)

    /*
        @Query("UPDATE events SET status = :status WHERE id = :id")
        suspend fun markTaskDone(id: Int, status: String)

    */

    /* То же самое замечание, что и сверху (если удаление вообще нужно)
        @Query("DELETE FROM events WHERE id = :id")
        suspend fun deleteTicketById(id: Int)

    */
}
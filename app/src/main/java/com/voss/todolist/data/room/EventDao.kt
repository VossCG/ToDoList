package com.voss.todolist.data.room

import androidx.room.*
import com.voss.todolist.data.Event
import kotlinx.coroutines.flow.Flow


@Dao
interface EventDao {

    // ORDER BY  dateInteger   排序結果  ASC 代表由小往大 DESC 大到小
    @Query("select * from Event ORDER BY dateInteger ASC")
    fun getAll(): Flow<List<Event>>

    @Query("select * from event where title like:title")
    suspend fun getEventByTitle(title: String): List<Event>

    @Query("select * from event where content like:content")
    suspend fun getEventByContent(content: String): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("DELETE from Event")
    suspend fun clearAll()

    @Query("select * from Event where id= :id")
    suspend fun getEvent(id: Int): Event

    @Query("select * from Event where dateInteger between :startDate and :endDate")
    suspend fun getEventByRange(startDate: Int, endDate: Int): List<Event>
}


package com.voss.todolist.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.voss.todolist.Data.EventTypes


@Dao
interface EventDao {

    // ORDER BY  dateInteger   排序結果  ASC 代表由小往大 DESC 大到小
    @Query("select * from EventTypes ORDER BY dateInteger ASC")
    fun getAll(): LiveData<List<EventTypes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventTypes)

    @Delete
    suspend fun delete(event: EventTypes)

    @Update
    suspend fun update(event: EventTypes)

    @Query("DELETE from EventTypes")
    suspend fun clearAll()

    @Query("select * from EventTypes where id= :id")
    suspend fun getEvent(id: Int): EventTypes

    @Query("select * from EventTypes where dateInteger between :startDate and :endDate")
    suspend fun getEventByRange(startDate: Int, endDate: Int): List<EventTypes>
}


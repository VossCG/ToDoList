package com.voss.todolist.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.voss.todolist.Data.EventTypes


@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: EventTypes)

    @Delete
    fun delete(event: EventTypes)

    @Update
    fun update(event: EventTypes)

    // ORDER BY  dateInteger   排序結果  ASC 代表由小往大 DESC 大到小
    @Query("select * from EventTypes ORDER BY dateInteger ASC")
    fun getAll(): LiveData<List<EventTypes>>

    @Query("DELETE from EventTypes")
    fun clearAll()

}


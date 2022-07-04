package com.voss.todolist.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.voss.todolist.data.EventTypes


@Database(entities = [EventTypes::class],version = 1,exportSchema = false)
abstract class EventDataBase : RoomDatabase() {
    abstract fun eventRoomDao(): EventDao
}
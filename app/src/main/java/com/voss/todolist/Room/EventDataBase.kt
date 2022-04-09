package com.voss.todolist.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.voss.todolist.Data.EventTypes


@Database(entities = [EventTypes::class],version = 1,exportSchema = false)
abstract class EventDataBase : RoomDatabase() {
    abstract fun eventRoomDao(): EventDao

    companion object {
        private var INSTANCE: EventDataBase? = null

        fun getInstance(context: Context): EventDataBase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, EventDataBase::class.java,
                        "EventDataNew.db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
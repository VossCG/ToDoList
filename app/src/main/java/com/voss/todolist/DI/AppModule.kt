package com.voss.todolist.DI

import android.content.Context
import androidx.room.Room
import com.voss.todolist.Data.EventRepository
import com.voss.todolist.Room.EventDao
import com.voss.todolist.Room.EventDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): EventDataBase {
        return Room.databaseBuilder(context, EventDataBase::class.java, "EventDataBase.db")
            .build()
    }

    @Provides
    fun providesRoomDao(database: EventDataBase): EventDao {
        return database.eventRoomDao()
    }

    @Provides
    @Singleton
    fun providesRepo(eventDao: EventDao): EventRepository {
        return EventRepository(eventDao)
    }
}
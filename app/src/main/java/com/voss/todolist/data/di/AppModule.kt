package com.voss.todolist.data.di

import android.content.Context
import androidx.room.Room
import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.room.EventDao
import com.voss.todolist.data.room.EventDataBase
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FilterDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
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
    fun providesRepo(eventDao: EventDao): EventRepositoryImp {
        return EventRepositoryImp(eventDao)
    }

    @Provides
    @Singleton
    fun providesChangeDateUseCase(repository: EventRepositoryImp): DaoDataUseCase {
        return DaoDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFilterDateUseCase(repository: EventRepositoryImp): FilterDataUseCase {
        return FilterDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFormatDateUseCase(repository: EventRepositoryImp):FormatDateUseCase {
        return FormatDateUseCase(repository)
    }
}
package com.voss.todolist.data.di

import android.content.Context
import androidx.room.Room
import com.voss.todolist.data.EventRepository
import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.room.EventDao
import com.voss.todolist.data.room.EventDataBase
import com.voss.todolist.domain.*
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
        return Room.databaseBuilder(context, EventDataBase::class.java, "EventData.db")
            .build()
    }

    @Provides
    fun providesRoomDao(database: EventDataBase): EventDao {
        return database.eventRoomDao()
    }

    @Provides
    @Singleton
    fun providesRepo(eventDao: EventDao): EventRepository {
        return EventRepositoryImp(eventDao)
    }

    @Provides
    @Singleton
    fun providesSearchFactorChangeUseCase(): GetSearchFlowUseCase {
        return GetSearchFlowUseCase()
    }

    @Provides
    @Singleton
    fun provideGetEventFlowByDateUseCase(): GetEventFlowByDateUseCase {
        return GetEventFlowByDateUseCase()
    }

    @Provides
    @Singleton
    fun providesFormatDateUseCase(): GetFormatDateUseCase {
        return GetFormatDateUseCase()
    }

    @Provides
    @Singleton
    fun providesMonthlyEventUseCase(): GetMonthlyEventUseCase {
        return GetMonthlyEventUseCase()
    }

    @Provides
    @Singleton
    fun provideSingleDayEventUseCase(): GetSingleDayEventUseCase {
        return GetSingleDayEventUseCase()
    }

    @Provides
    @Singleton
    fun provideDateIntegerUseCase(): GetDateIntegerUseCase {
        return GetDateIntegerUseCase()
    }

    @Provides
    @Singleton
    fun provideTransformDateToTimeUseCase(): TransformDateToTimeUseCase {
        return TransformDateToTimeUseCase()
    }
}
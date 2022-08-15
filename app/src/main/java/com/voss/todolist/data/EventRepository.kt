package com.voss.todolist.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    val eventDataList: LiveData<List<Event>>

    val eventFlow:Flow<List<Event>>

    suspend fun insertEvent(event: Event)

    suspend fun updateEvent(event: Event)

    suspend fun deleteEvent(event: Event)

    suspend fun getEventByRange(start: Int, end: Int): List<Event>

    suspend fun clearAll()

    suspend fun getEventByTitle(title:String):List<Event>

    suspend fun getEventByContent(content:String):List<Event>
}
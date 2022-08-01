package com.voss.todolist.data

import androidx.lifecycle.LiveData

interface EventRepository {
    val eventDataList: LiveData<List<Event>>

    suspend fun insertEvent(event: Event)

    suspend fun updateEvent(event: Event)

    suspend fun deleteEvent(event: Event)

    suspend fun getEventByRange(start: Int, end: Int): List<Event>

    suspend fun clearAll()
}
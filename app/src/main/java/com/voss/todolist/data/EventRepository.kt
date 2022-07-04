package com.voss.todolist.data

interface EventRepository {

    suspend fun insertEvent(event: EventTypes)

    suspend fun updateEvent(event: EventTypes)

    suspend fun deleteEvent(event: EventTypes)

    suspend fun getEventByRange(start: Int, end: Int): List<EventTypes>

    suspend fun clearAll()
}
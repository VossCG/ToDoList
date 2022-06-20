package com.voss.todolist.Data

import androidx.lifecycle.LiveData
import com.voss.todolist.Room.EventDao
import kotlinx.coroutines.delay

class EventRepository(private val eventDao: EventDao) {

    val eventDataList: LiveData<List<EventTypes>> = eventDao.getAll()

    suspend fun insertEvent(event: EventTypes) {
        eventDao.insert(event)
    }

    suspend fun updateEvent(event: EventTypes) {
        eventDao.update(event)
    }

    suspend fun deleteEvent(event: EventTypes) {
        eventDao.delete(event)
    }

    suspend fun getEventByRange(start: Int, end: Int): List<EventTypes> {
        return eventDao.getEventByRange(start, end)
    }

    suspend fun clearAll() {
        eventDao.clearAll()
    }
}
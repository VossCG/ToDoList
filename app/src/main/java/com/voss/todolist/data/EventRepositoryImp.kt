package com.voss.todolist.data

import androidx.lifecycle.LiveData
import com.voss.todolist.data.room.EventDao

class EventRepositoryImp(private val eventDao: EventDao) : EventRepository {

    val eventDataList: LiveData<List<Event>> = eventDao.getAll()

    override suspend fun insertEvent(event: Event) {
        eventDao.insert(event)
    }

    override suspend fun updateEvent(event: Event) {
        eventDao.update(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.delete(event)
    }

    override suspend fun getEventByRange(start: Int, end: Int): List<Event> {
        return eventDao.getEventByRange(start, end)
    }

    override suspend fun clearAll() {
        eventDao.clearAll()
    }
}
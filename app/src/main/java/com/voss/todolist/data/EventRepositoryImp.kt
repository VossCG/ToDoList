package com.voss.todolist.data

import androidx.lifecycle.LiveData
import com.voss.todolist.data.room.EventDao

class EventRepositoryImp(private val eventDao: EventDao) : EventRepository {

    val eventDataList: LiveData<List<EventTypes>> = eventDao.getAll()

    override suspend fun insertEvent(event: EventTypes) {
        eventDao.insert(event)
    }

    override suspend fun updateEvent(event: EventTypes) {
        eventDao.update(event)
    }

    override suspend fun deleteEvent(event: EventTypes) {
        eventDao.delete(event)
    }

    override suspend fun getEventByRange(start: Int, end: Int): List<EventTypes> {
        return eventDao.getEventByRange(start, end)
    }

    override suspend fun clearAll() {
        eventDao.clearAll()
    }
}
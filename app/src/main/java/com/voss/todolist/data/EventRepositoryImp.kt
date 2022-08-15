package com.voss.todolist.data

import androidx.lifecycle.LiveData
import com.voss.todolist.data.room.EventDao
import kotlinx.coroutines.flow.Flow

class EventRepositoryImp(private val eventDao: EventDao) : EventRepository {

    override val eventDataList: LiveData<List<Event>> = eventDao.getAll()
    override val eventFlow: Flow<List<Event>> get() = eventDao.getAllWithFlow()

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

    override suspend fun getEventByTitle(title: String): List<Event> {
        return eventDao.getEventByTitle(title)
    }

    override suspend fun getEventByContent(content: String): List<Event> {
        return eventDao.getEventByContent(content)
    }
}
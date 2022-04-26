package com.voss.todolist.Data

import androidx.lifecycle.LiveData
import com.voss.todolist.Room.EventDao
import kotlinx.coroutines.delay

class EventRepository(private val eventDao: EventDao) {

    val eventDataList: LiveData<List<EventTypes>> = eventDao.getAll()

    suspend fun insertEvent(event: EventTypes) {
        eventDao.insert(event)
    }
    suspend fun updateEvent(event: EventTypes){
        eventDao.update(event)
    }
    suspend fun deleteEvent(event: EventTypes){
        eventDao.delete(event)
    }

}
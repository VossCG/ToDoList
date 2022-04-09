package com.voss.todolist.Data

import androidx.lifecycle.LiveData
import com.voss.todolist.Room.EventDao
import kotlinx.coroutines.delay

class EventRepository(private val eventDao: EventDao) {

    val eventDataList: LiveData<List<EventTypes>> = eventDao.getAll()

    suspend fun insertEvent(event: EventTypes) {
        delay(10)
        eventDao.insert(event)
    }
    suspend fun updateEvent(event: EventTypes){
        delay(10)
        eventDao.update(event)
    }
    suspend fun deleteEvent(event: EventTypes){
        delay(10)
        eventDao.delete(event)
    }

}
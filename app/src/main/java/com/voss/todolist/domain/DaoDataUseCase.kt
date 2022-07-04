package com.voss.todolist.domain

import androidx.lifecycle.LiveData
import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.EventTypes
import javax.inject.Inject

class DaoDataUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    suspend fun deleteEvent(eventType: EventTypes) {
        repository.deleteEvent(eventType)
    }

    suspend fun addEvent(eventType: EventTypes) {
        repository.insertEvent(eventType)
    }

    suspend fun updateEvent(eventType: EventTypes) {
        repository.updateEvent(eventType)
    }
    fun getAll(): LiveData<List<EventTypes>> {
        return repository.eventDataList
    }
}
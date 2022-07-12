package com.voss.todolist.domain

import androidx.lifecycle.LiveData
import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.Event
import javax.inject.Inject

class DaoDataUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    suspend fun deleteEvent(eventType: Event) {
        repository.deleteEvent(eventType)
    }

    suspend fun addEvent(eventType: Event) {
        repository.insertEvent(eventType)
    }

    suspend fun updateEvent(eventType: Event) {
        repository.updateEvent(eventType)
    }
    fun getAll(): LiveData<List<Event>> {
        return repository.eventDataList
    }
}
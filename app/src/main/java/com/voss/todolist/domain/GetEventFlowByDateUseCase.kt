package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import kotlinx.coroutines.flow.*

class GetEventFlowByDateUseCase {

    operator fun invoke(date: String, repository: EventRepository): Flow<List<Event>> {
        return repository.eventFlow.map { events ->
            events.filter { it.date == date }
        }
    }
}

package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMonthlyEventUseCase {

    operator fun invoke(year: Int, month: Int, repository: EventRepository): Flow<List<Event>> {
        return repository.eventFlow.map { events ->
            events.filter { it.getYear() == year && it.getMonth() == month }
        }
    }
}
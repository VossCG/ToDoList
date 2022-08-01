package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import javax.inject.Inject

class GetMonthlyEventUseCase @Inject constructor(private val repository: EventRepository) {

    operator fun invoke (year: Int, month: Int): List<Event> {
        return repository.eventDataList.value?.filter {
            it.getYear() == year && it.getMonth() == month
        } ?: emptyList()
    }
}
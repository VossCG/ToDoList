package com.voss.todolist.domain

import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.Event
import javax.inject.Inject

class GetMonthlyEventUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    operator fun invoke (year: Int, month: Int): List<Event> {
        return repository.eventDataList.value?.filter {
            it.getYear() == year && it.getMonth() == month
        } ?: emptyList()
    }
}
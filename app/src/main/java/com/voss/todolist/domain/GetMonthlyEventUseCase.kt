package com.voss.todolist.domain

import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.EventTypes
import javax.inject.Inject

class GetMonthlyEventUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    operator fun invoke (year: Int, month: Int): List<EventTypes> {
        return repository.eventDataList.value?.filter {
            it.getYear() == year && it.getMonth() == month
        } ?: emptyList()
    }
}
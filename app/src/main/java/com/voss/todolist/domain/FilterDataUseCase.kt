package com.voss.todolist.domain

import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.EventTypes
import javax.inject.Inject

class FilterDataUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    fun getCurrentMonthEvent(year: Int, month: Int): List<EventTypes> {
        return repository.eventDataList.value!!.filter { it.getYear() == year && it.getMonth() == month }
    }



    fun filterDataWithFactor(inputData: String, filterFactor: String): List<EventTypes> {
        return when (filterFactor) {
            "title" -> {
                repository.eventDataList.value?.filter { it.title.contains(inputData) }
                    ?: emptyList()
            }
            "content" -> repository.eventDataList.value?.filter { it.content.contains(inputData) }
                ?: emptyList()
            else -> emptyList()
        }
    }
}
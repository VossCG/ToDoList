package com.voss.todolist.domain

import com.voss.todolist.data.EventRepositoryImp
import com.voss.todolist.data.Event
import javax.inject.Inject

class GetEventByKeyWordUseCase @Inject constructor(private val repository: EventRepositoryImp) {

    operator fun invoke(keyWord: String, filterFactor: String): List<Event> {
        return when (filterFactor) {
            "title" -> {
                repository.eventDataList.value?.filter { it.title.contains(keyWord) }
                    ?: emptyList()
            }
            "content" -> repository.eventDataList.value?.filter { it.content.contains(keyWord) }
                ?: emptyList()
            else -> emptyList()
        }
    }
}
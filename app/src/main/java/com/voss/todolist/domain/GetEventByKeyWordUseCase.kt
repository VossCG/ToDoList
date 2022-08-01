package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import javax.inject.Inject

class GetEventByKeyWordUseCase @Inject constructor(repository: EventRepository) {

    private val eventDataList = repository.eventDataList

    operator fun invoke(keyWord: String, filterFactor: String): List<Event> {
        return when (filterFactor) {
            "title" -> {
                eventDataList.value?.filter { it.title.contains(keyWord) }
                    ?: emptyList()
            }
            "content" ->
                eventDataList.value?.filter { it.content.contains(keyWord) }
                    ?: emptyList()
            else -> emptyList()
        }
    }
}
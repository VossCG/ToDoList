package com.voss.todolist.domain

import com.voss.todolist.data.Event

class GetEventByKeyWordUseCase {

    operator fun invoke(
        eventList: List<Event>,
        keyWord: String,
        filterFactor: String
    ): List<Event> {
        return when (filterFactor) {
            "title" -> {
                eventList.filter { it.title.contains(keyWord) }
            }
            "content" ->
                eventList.filter { it.content.contains(keyWord) }
            else -> emptyList()
        }
    }
}
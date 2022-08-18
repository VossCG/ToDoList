package com.voss.todolist.domain

import android.util.Log
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchFlowUseCase {
    operator fun invoke(repo: EventRepository, keyWord: String, factor: String): Flow<List<Event>> {
        Log.d("GetEventUseCase", "filter SearchEvent flow")
        return when (factor) {
            "title" -> {
                Log.d("GetEventUseCase", "filter Title")
                repo.eventFlow.map { events ->
                    events.filter { it.title.contains(keyWord) }
                }
            }
            "content" -> {
                Log.d("GetEventUseCase", "filter Content")
                repo.eventFlow.map { events ->
                    events.filter { it.content.contains(keyWord) }
                }
            }
            else -> repo.eventFlow.map { events ->
                events.filter { it.title == keyWord }
            }
        }
    }
}

package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.presentation.viewModel.SearchFactor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchFlowUseCase {
    operator fun invoke(repo: EventRepository, keyWord: String, factor: SearchFactor): Flow<List<Event>> {
        return when (factor) {
            SearchFactor.Title -> {
                repo.eventFlow.map { events ->
                    events.filter { it.title.contains(keyWord) }
                }
            }
            SearchFactor.Content -> {
                repo.eventFlow.map { events ->
                    events.filter { it.content.contains(keyWord) }
                }
            }
        }
    }
}

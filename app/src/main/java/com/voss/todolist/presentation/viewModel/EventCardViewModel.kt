package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventCardViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getEventFlowByDateUseCase: GetEventFlowByDateUseCase
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<List<Event>>(emptyList())
    val uiState: StateFlow<List<Event>> = _uiState

    fun getEventFlow(date: String): Flow<List<Event>> {
        return getEventFlowByDateUseCase.invoke(date, repository).distinctUntilChanged()
    }

    fun setUiStateEvent(date: String) {
        viewModelScope.launch {
            getEventFlowByDateUseCase.invoke(date, repository).collectLatest { events ->
                _uiState.value = events
            }
        }
    }

    fun deleteEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(eventType)
        }
    }

    fun addEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEvent(eventType)
        }
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month - 1, day)
    }
}

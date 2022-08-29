package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetSearchFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val getSearchFlowUseCase: GetSearchFlowUseCase
) : AndroidViewModel(application) {

    private val _keyWordState = MutableStateFlow("")
    val keyWordState: StateFlow<String> = _keyWordState

    private val _factorState = MutableStateFlow(SearchFactor.Title)
    val factorState: StateFlow<SearchFactor> = _factorState

    private val _eventState = MutableStateFlow<List<Event>>(emptyList())
    val eventState: StateFlow<List<Event>> = _eventState

    fun setSearchStateFactor(factor: SearchFactor) {
        _factorState.value = factor
    }

    fun setSearchStateKeyWord(keyWord: String) {
        _keyWordState.value = keyWord
    }

    fun setEventState(keyWord: String) {
        viewModelScope.launch {
            getSearchEventFlow(keyWord).collectLatest { events ->
                _eventState.value = events
            }
        }
    }
    private fun getSearchEventFlow(keyWord: String): Flow<List<Event>> {
        if (keyWord.isEmpty()) return flow { emit(emptyList()) }
        return getSearchFlowUseCase.invoke(repository, keyWord, _factorState.value)
    }

    fun deleteEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(eventTypes)
        }
    }
}

enum class SearchFactor {
    Title, Content
}


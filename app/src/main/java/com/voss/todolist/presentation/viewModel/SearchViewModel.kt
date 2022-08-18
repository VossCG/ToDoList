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
    private val getSearchFlowUseCase: GetSearchFlowUseCase,
) : AndroidViewModel(application) {

    private val _filterFactor = MutableLiveData<String>("title")
    val filterFactor: LiveData<String> = _filterFactor

    private val _keyWord = MutableLiveData<String>("")
    val keyWord: LiveData<String> = _keyWord

    private val _searchEventState: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    val searchEventState: StateFlow<List<Event>> = _searchEventState

    // this is a cold flow
    fun setUiEvent(keyWord: String) {
        viewModelScope.launch {
            getSearchFlowUseCase(
                repository,
                keyWord,
                _filterFactor.value!!
            ).collectLatest { events ->
                if (keyWord.isEmpty())
                    _searchEventState.value = emptyList()
                else
                    _searchEventState.value = events
            }
        }
    }

    // return flow to Ui without use stateFlow. this is a one time shot flow
    fun getSearchEventFlow(keyWord: String): Flow<List<Event>> {
        if (keyWord.isEmpty()) return flow { emit(emptyList()) }
        return when (_filterFactor.value) {
            "title" -> {
                repository.eventFlow.map { events -> events.filter { it.title.contains(keyWord) } }
            }
            "content" -> {
                repository.eventFlow.map { events -> events.filter { it.content.contains(keyWord) } }
            }
            else -> emptyFlow()
        }
    }

    fun setSearchFactor(factor: String) {
        _filterFactor.postValue(factor)
    }

    fun setKeyWord(keyWord: String) {
        _keyWord.postValue(keyWord)
    }

    fun deleteEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(eventTypes)
        }
    }
}
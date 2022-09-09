package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetDateIntegerUseCase
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.presentation.uiState.UpdateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateEventViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val formatDateUseCase: GetFormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : AndroidViewModel(application) {

    private var _updateUiState: UpdateUiState = UpdateUiState("", "", "", "", 0)
    val uiState: UpdateUiState get() = _updateUiState

    fun updateEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(eventTypes)
        }
    }

    fun setUiState(uiState: UpdateUiState) {
        _updateUiState = uiState
    }

    fun setStateDateInteger(year: Int, month: Int, day: Int) {
        _updateUiState = _updateUiState.copy(dateInteger = getDateIntegerUseCase(year, month, day))
    }

    fun setStateDate(date: String) {
        _updateUiState = _updateUiState.copy(date = date)
    }

    fun setStateType(eventType: String) {
        _updateUiState = _updateUiState.copy(eventType = eventType)
    }

    fun setStateContent(content: String) {
        _updateUiState = _updateUiState.copy(content = content)
    }

    fun setStateTitle(title: String) {
        _updateUiState = _updateUiState.copy(title = title)
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day)
    }
}
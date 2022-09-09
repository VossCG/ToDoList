package com.voss.todolist.presentation.viewModel

import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.domain.GetDateIntegerUseCase
import com.voss.todolist.presentation.uiState.EditEventUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
    private val repository: EventRepository,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : ViewModel() {

    // to write
    private var _editUiState: EditEventUiState = EditEventUiState("工作", "", "", "yyyy/mm/dd", 0)
    // to read
    val editUiState: EditEventUiState get() = _editUiState

    fun insertEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEvent(eventTypes)
        }
    }

    fun setStateDateInteger(year: Int, month: Int, day: Int) {
        _editUiState = _editUiState.copy(dateInteger = getDateIntegerUseCase(year, month, day))
    }

    fun setStateDate(date: String) {
        _editUiState = _editUiState.copy(date = date)
    }

    fun setStateType(eventType: String) {
        _editUiState = _editUiState.copy(eventType = eventType)
    }

    fun setStateContent(content: String) {
        _editUiState = _editUiState.copy(content = content)
    }

    fun setStateTitle(title: String) {
        _editUiState = _editUiState.copy(title = title)
    }

    fun getFormatData(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month, day)
    }
}
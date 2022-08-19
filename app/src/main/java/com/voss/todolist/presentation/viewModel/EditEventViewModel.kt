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

    val editUiState: EditEventUiState = EditEventUiState("工作", "", "", "yyyy/mm/dd",0)

    fun insertEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEvent(eventTypes)
        }
    }

    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return getDateIntegerUseCase.invoke(year, month, day)
    }
    fun getFormatData(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month, day)
    }
}
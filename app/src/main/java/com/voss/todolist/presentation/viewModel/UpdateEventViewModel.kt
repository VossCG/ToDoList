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

    val uiState: UpdateUiState = UpdateUiState("","", "", "", 0)

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day)
    }

    fun updateEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(eventTypes)
        }
    }

    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return getDateIntegerUseCase(year, month, day)
    }
}
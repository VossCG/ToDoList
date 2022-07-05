package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import com.voss.todolist.domain.GetDateIntegerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateEventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : AndroidViewModel(application) {

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day)
    }
    fun updateEvent(eventTypes: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.updateEvent(eventTypes)
        }
    }
    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return getDateIntegerUseCase(year, month, day)
    }
}
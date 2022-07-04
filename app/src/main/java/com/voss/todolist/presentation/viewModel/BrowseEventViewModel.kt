package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FilterDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BrowseEventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val filterDataUseCase: FilterDataUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<EventTypes>> = daoDataUseCase.getAll()

    private val _selectItemDay = MutableLiveData(1)
    val selectItemDay: LiveData<Int> = _selectItemDay

    private val _currentYear = MutableLiveData(2022)
    val currentYear: LiveData<Int> = _currentYear

    private val _currentMonth = MutableLiveData(3)
    val currentMonth: LiveData<Int> = _currentMonth
    // -------------------------------------------------------------

    fun setSelectItemDay(day: Int) {
        _selectItemDay.value = day
    }

    fun setYear(year: Int) {
        _currentYear.value = year
    }

    fun setMonth(month: Int) {
        _currentMonth.value = month
    }

    fun plusYear(plus: Int) {
        _currentYear.value = _currentYear.value?.plus(plus)
    }

    // useCase test
    fun deleteEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventType)
        }
    }

    fun getMonthEvent(month: Int): List<EventTypes> {
        return filterDataUseCase.getCurrentMonthEvent(_currentYear.value!!, month)
    }

    fun getDateFormat(year: Int, month: Int, day: Int, calendar: Calendar): String {
        return formatDateUseCase(year, month, day, calendar)
    }
}

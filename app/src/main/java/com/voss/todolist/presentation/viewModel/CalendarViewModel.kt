package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.domain.GetMonthlyEventUseCase
import com.voss.todolist.domain.GetSingleDayEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getSingleDayEventUseCase: GetSingleDayEventUseCase,
    private val getMonthlyEventUseCase: GetMonthlyEventUseCase
) : AndroidViewModel(application) {

    private val calendar = Calendar.getInstance(Locale.TAIWAN)

    private val _yearState = MutableStateFlow<Int>(calendar.get(Calendar.YEAR))
    val yearState: StateFlow<Int> = _yearState

    private val _monthState = MutableStateFlow<Int>(calendar.get(Calendar.MONTH)+1)
    val monthState: StateFlow<Int> = _monthState

    private val _selectDayState = MutableStateFlow<Int>(calendar.get(Calendar.DAY_OF_MONTH))
    val selectDayState: StateFlow<Int> = _selectDayState

    private val _monthEventState = MutableStateFlow<List<Event>>(emptyList())
    val monthEventState :StateFlow<List<Event>> = _monthEventState

    private val _dateState = MutableStateFlow<String>("yyyy/mm/dd")
    val dateState: StateFlow<String> = _dateState

    fun setMonthEvent(events:List<Event>){
        _monthEventState.value = events
    }

    fun changeYear(year: Int) {
        _yearState.value += year
    }

    fun setYear(year: Int) {
        _yearState.value = year
    }

    fun setMonth(month: Int) {
        _monthState.value = month
    }

    fun setDay(day: Int) {
        _selectDayState.value = day
    }

    fun setDate(date: String) {
        _dateState.value = date
    }

    fun getSingleDayEvent(date: String): Flow<List<Event>> {
        return getSingleDayEventUseCase.invoke(date, repository)
    }

    fun getMonthEvent(year: Int, month: Int): Flow<List<Event>> {
        return getMonthlyEventUseCase.invoke(year, month, repository)
    }

    fun getCurrentDate(): String {
        return getFormatDateUseCase(_yearState.value, _monthState.value-1, _selectDayState.value)
    }

    fun getCurrentMonthOfDays(month: Int, year: Int): Int {
        // 如果為閏年的二月，回傳天數為29
        if (year % 4 == 0 && month == 2) {
            return 29
        }
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> 28
            else -> 0
        }
    }

    fun getFirstWeekOfMonth(pagePosition: Int, mCalendar: Calendar): Int {
        mCalendar.set(Calendar.YEAR, _yearState.value)
        mCalendar.set(Calendar.MONTH, pagePosition - 1)
        mCalendar.set(Calendar.DAY_OF_MONTH, 1)
        return mCalendar.get(Calendar.DAY_OF_WEEK)
    }
}

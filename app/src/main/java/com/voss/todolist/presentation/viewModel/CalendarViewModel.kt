package com.voss.todolist.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.domain.GetMonthlyEventUseCase
import com.voss.todolist.domain.GetSingleDayEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getMonthlyEventUseCase: GetMonthlyEventUseCase,
    private val getSingleDayEventUseCase: GetSingleDayEventUseCase
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<Event>> = repository.eventDataList

    private val calendar = Calendar.getInstance(Locale.TAIWAN)

    private val _selectItemDay = MutableLiveData<Int>()
    val selectItemDay: LiveData<Int> = _selectItemDay

    private val _currentYear = MutableLiveData<Int>()
    val currentYear: LiveData<Int> = _currentYear

    private val _currentMonth = MutableLiveData<Int>()
    val currentMonth: LiveData<Int> = _currentMonth

    // -------------------------------------------------------------

    init {
        _selectItemDay.value = calendar.get(Calendar.DAY_OF_MONTH)
        _currentYear.value = calendar.get(Calendar.YEAR)
        _currentMonth.value = calendar.get(Calendar.MONTH) + 1
    }

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

    fun getMonthEvent(month: Int): List<Event> {
        return getMonthlyEventUseCase(
            _currentYear.value!!,
            month,
            repository.eventDataList.value ?: emptyList()
        )
    }

    fun getSingleDayEvent(): List<Event> {
        return getSingleDayEventUseCase.invoke(
            _currentYear.value!!,
            _currentMonth.value!!,
            _selectItemDay.value!!,
            repository.eventDataList.value ?: emptyList()
        )
    }

    fun getCurrentDate(): String {
        return getFormatDateUseCase(
            _currentYear.value!!,
            _currentMonth.value!! - 1,
            _selectItemDay.value!!
        )
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
        mCalendar.set(Calendar.YEAR, currentYear.value!!)
        mCalendar.set(Calendar.MONTH, pagePosition - 1)
        mCalendar.set(Calendar.DAY_OF_MONTH, 1)
        return mCalendar.get(Calendar.DAY_OF_WEEK)
    }
}

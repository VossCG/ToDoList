package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.domain.GetMonthlyEventUseCase
import com.voss.todolist.domain.GetSingleDayEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getMonthlyEventUseCase: GetMonthlyEventUseCase,
    private val getSingleDayEventUseCase: GetSingleDayEventUseCase
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<Event>> = daoDataUseCase.getAll()

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
        _currentMonth.value = calendar.get(Calendar.MONTH)+1
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
    // useCase test
    fun deleteEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventType)
        }
    }
    fun addEvent(eventType: Event){
        viewModelScope.launch(Dispatchers.IO){
            daoDataUseCase.addEvent(eventType)
        }
    }
    fun getMonthEvent(month: Int): List<Event> {
        return getMonthlyEventUseCase(_currentYear.value!!, month)
    }
    fun getSingleDayEvent(): List<Event> {
        return getSingleDayEventUseCase.invoke(
            _currentYear.value!!,
            _currentMonth.value!!,
            _selectItemDay.value!!
        )
    }

    fun getCurrentDate(): String {
        return getFormatDateUseCase(
            _currentYear.value!!,
            _currentMonth.value!! - 1,
            _selectItemDay.value!!
        )
    }
}

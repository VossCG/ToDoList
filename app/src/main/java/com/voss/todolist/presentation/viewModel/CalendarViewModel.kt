package com.voss.todolist.presentation.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import com.voss.todolist.domain.GetMonthlyEventUseCase
import com.voss.todolist.domain.GetSingleDayEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val getMonthlyEventUseCase: GetMonthlyEventUseCase,
    private val getSingleDayEventUseCase: GetSingleDayEventUseCase
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<EventTypes>> = daoDataUseCase.getAll()

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
    fun deleteEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventType)
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "刪除完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMonthEvent(month: Int): List<EventTypes> {
        return getMonthlyEventUseCase(_currentYear.value!!, month)
    }

    fun getSingleDayEvent(): List<EventTypes> {
        return getSingleDayEventUseCase.invoke(
            _currentYear.value!!,
            _currentMonth.value!!,
            _selectItemDay.value!!
        )
    }

    fun getCurrentDate(): String {
        return formatDateUseCase(
            _currentYear.value!!,
            _currentMonth.value!! - 1,
            _selectItemDay.value!!
        )
    }

}

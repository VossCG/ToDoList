package com.voss.todolist.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.voss.todolist.Data.EventRepository
import com.voss.todolist.Data.EventTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BrowseEventViewModel @Inject constructor(
    application: Application,
    var repository: EventRepository
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<EventTypes>> = repository.eventDataList

    val selectItemDay = MutableLiveData<Int>(1)

    // think about and check again that must need the LiveData?

    private val _currentYear = MutableLiveData<Int>(2022)
    val currentYear: LiveData<Int> = _currentYear

    private val _currentMonth = MutableLiveData<Int>(3)
    val currentMonth: LiveData<Int> = _currentMonth

    private val _currentDay = MutableLiveData<Int>(8)
    val currentDay: LiveData<Int> = _currentDay

    // -------------------------------------------------------------

    fun setItemDay(position: Int) {
        selectItemDay.value = position
    }

    fun setYear(year: Int) {
        _currentYear.value = year
    }

    fun setMonth(month: Int) {
        _currentMonth.value = month
    }

    fun setDay(day: Int) {
        _currentDay.value = day
    }

    fun plusYear(plus: Int) {
        _currentYear.value = _currentYear.value?.plus(plus)
    }

    fun plusMonth(plus: Int) {
        _currentMonth.value = _currentMonth.value?.plus(plus)
    }

    fun addEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEvent(eventType)
        }
    }

    fun updateEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(eventType)
        }
    }

    fun deleteEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(eventType)
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "刪除完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMonthEvent(month: Int): List<EventTypes> {
        return readAllEvent.value!!.filter {
            it.getMonth() == month
        }
    }
}

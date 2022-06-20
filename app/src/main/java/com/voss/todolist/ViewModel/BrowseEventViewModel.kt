package com.voss.todolist.ViewModel

import android.app.Application
import android.icu.text.NumberFormat
import android.util.Log
import androidx.lifecycle.*
import com.voss.todolist.Data.EventRepository
import com.voss.todolist.Data.EventTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BrowseEventViewModel @Inject constructor(
    application: Application,
    var repository: EventRepository
) : AndroidViewModel(application) {

    private lateinit var list: LiveData<List<EventTypes>>
    private val _currentYear = MutableLiveData<Int>(2022)
    val currentYear: LiveData<Int> = _currentYear

    private val _currentMonth = MutableLiveData<Int>(3)
    val currentMonth: LiveData<Int> = _currentMonth

    private val _currentDay = MutableLiveData<Int>(8)
    val currentDay: LiveData<Int> = _currentDay

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

    suspend fun getCurrentMonthData(start: Int, end: Int): List<EventTypes> {
        var list = emptyList<EventTypes>()
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "ViewModel scope")
            list = repository.getEventByRange(start, end)
        }.join()
        return list
    }

    fun getMonthInteger(month: Int): Int {
        return currentYear.value!! * 10000 + month * 100
    }
}

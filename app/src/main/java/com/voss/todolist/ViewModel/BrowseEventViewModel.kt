package com.voss.todolist.ViewModel

import android.app.Application
import android.icu.text.SimpleDateFormat
import android.widget.Toast
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

    val readAllEvent: LiveData<List<EventTypes>> = repository.eventDataList

    private val _selectItemDay = MutableLiveData<Int>(1)
    val selectItemDay :LiveData<Int> = _selectItemDay
    // think about and check again that must need the LiveData?

    private val _currentYear = MutableLiveData<Int>(2022)
    val currentYear: LiveData<Int> = _currentYear

    private val _currentMonth = MutableLiveData<Int>(3)
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
            it.getMonth() == month && it.getYear() == currentYear.value
        }
    }
    fun getDateFormat(year: Int, month: Int, day: Int,calendar: Calendar): String {
        calendar.set(year, month-1, day)
        val date = calendar.time
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN)
        return format.format(date)
    }

    fun convertWeekToChinese(week: Int): String {
        val currentWeek = when (week) {
            1 -> "星期一"
            2 -> "星期一"
            3 -> "星期一"
            4 -> "星期一"
            5 -> "星期一"
            6 -> "星期一"
            7 -> "星期一"
            else -> "error"
        }
        return currentWeek
    }

}

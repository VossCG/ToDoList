package com.voss.todolist.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.voss.todolist.Data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BrowseEventViewModel  @Inject constructor(application: Application, var repository: EventRepository) : AndroidViewModel(application) {
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    val currentMonth = MutableLiveData<Int>()
    val currentYear = MutableLiveData<Int>()
    val currentDay = MutableLiveData<Int>()
    init {
        currentYear.value = calendar.get(Calendar.YEAR)
        currentMonth.value = calendar.get(Calendar.MONTH)+1
        currentDay.value = calendar.get(Calendar.DAY_OF_MONTH)
    }
    fun changeYear(year:Int){
        currentMonth.value = year
    }
    fun changeMonth(month:Int){
        currentMonth.value = month
    }
    fun changeDay(day:Int){
        currentMonth.value = day
    }
}
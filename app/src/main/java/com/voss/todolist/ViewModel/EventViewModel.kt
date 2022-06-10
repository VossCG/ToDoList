package com.voss.todolist.ViewModel

import android.app.Application
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.voss.todolist.Data.EventRepository
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Room.EventDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(application: Application, var repository: EventRepository) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<EventTypes>>
    val date = MutableLiveData<String>()
    val filterFactor = MutableLiveData<String>()

    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }

    init {
        date.value = "YY/MM/DD"
        filterFactor.value = "title"
        readAllEvent = repository.eventDataList
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

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        calendar.set(year, month, day)
        val date = calendar.time
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN)
        return format.format(date)
    }

    fun setDate(year: Int, month: Int, day: Int) {
        this.date.postValue(getDateFormat(year, month, day))
    }

    fun setFilterFactor(factor: String) {
        this.filterFactor.postValue(factor)
    }


    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return year * 10000 + month * 100 + day
    }

    fun filterDataWithFactor(inputData: String): List<EventTypes> {
        val filterList = readAllEvent.value?.filter {
            when (filterFactor.value) {
                "title" -> it.title.contains(inputData)
                "content" -> it.content.contains(inputData)
                else -> {
                    false
                }
            }
        }
        return filterList ?: emptyList()
    }
}

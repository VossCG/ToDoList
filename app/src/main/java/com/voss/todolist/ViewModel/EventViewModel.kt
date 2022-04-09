package com.voss.todolist.ViewModel

import android.app.Application
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.voss.todolist.Data.EventRepository
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Room.EventDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class EventViewModel(application: Application) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<EventTypes>>
    val date = MutableLiveData<String>()
    private val repository: EventRepository
    private val calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }

    init {
        val eventDao = EventDataBase.getInstance(application).eventRoomDao()
        repository = EventRepository(eventDao)

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
        }
    }


    fun getDateFormat(year: Int, month: Int, day: Int): String {
        calendar.set(year, month, day)
        val date= calendar.time
        Log.d("ViewModel","$date")
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN)
        return format.format(date)
    }

    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return year * 10000 + month * 100 + day
    }


}
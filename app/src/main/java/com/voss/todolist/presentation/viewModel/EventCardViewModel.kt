package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventCardViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val getFormatDateUseCase: GetFormatDateUseCase
) : AndroidViewModel(application) {

    private val currentDate = MutableLiveData<String>()

    val dateEvent: LiveData<List<Event>> = liveData {
        repository.eventFlow.collect { events ->
            emit(events.filter { it.date == currentDate.value })
        }
    }

    fun setCurrentDate(date: String) {
        currentDate.postValue(date)
    }

    fun deleteEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(eventType)
        }
    }
    fun addEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEvent(eventType)
        }
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month - 1, day)
    }

}

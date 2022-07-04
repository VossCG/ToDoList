package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FilterDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val filterUseCase: FilterDataUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : AndroidViewModel(application) {

    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    val readAllEvent: LiveData<List<EventTypes>> = daoDataUseCase.getAll()
    private val filterFactor = MutableLiveData<String>()


    init {
        filterFactor.value = "title"
    }

    fun addEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.addEvent(eventType)
        }
    }

    fun updateEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.updateEvent(eventType)
        }
    }

    fun deleteEvent(eventType: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventType)
        }
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day, calendar)
    }

    fun setFilterFactor(factor: String) {
        this.filterFactor.postValue(factor)
    }

    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return year * 10000 + month * 100 + day
    }

    fun filterDataWithFactor(inputData: String): List<EventTypes> {
        return filterUseCase.filterDataWithFactor(inputData, filterFactor.value ?: "null")
    }
}

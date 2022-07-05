package com.voss.todolist.presentation.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.SearchFactorChangeUseCase
import com.voss.todolist.domain.FormatDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val SearchFactorChangeUseCase: SearchFactorChangeUseCase,
    private val formatDateUseCase: FormatDateUseCase,
) : AndroidViewModel(application) {

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
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "刪除完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day)
    }

    fun setSearchFactor(factor: String) {
        this.filterFactor.postValue(factor)
    }

    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return year * 10000 + month * 100 + day
    }

    fun filterDataWithFactor(inputData: String): List<EventTypes> {
        return SearchFactorChangeUseCase(inputData, filterFactor.value ?: "null")
    }
}

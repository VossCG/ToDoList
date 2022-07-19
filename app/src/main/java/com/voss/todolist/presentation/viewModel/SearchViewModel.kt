package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.Event
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.GetEventByKeyWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val getEventByKeyWordUseCase: GetEventByKeyWordUseCase,
) : AndroidViewModel(application) {

    private val _filterFactor = MutableLiveData<String>()
    val readAllEvent: LiveData<List<Event>> = daoDataUseCase.getAll()

    init {
        _filterFactor.value = "title"
    }

    fun setSearchFactor(factor: String) {
        _filterFactor.postValue(factor)
    }

    fun deleteEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventTypes)
        }
    }
    fun addEvent(eventTypes: Event){
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.addEvent(eventTypes)
        }
    }

    fun getFilterEvent(keyWord: String): List<Event> {
        return getEventByKeyWordUseCase(keyWord, _filterFactor.value ?: "null")
    }
}
package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import com.voss.todolist.domain.GetDateIntegerUseCase
import com.voss.todolist.domain.SearchFactorChangeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val searchFactorChangeUseCase: SearchFactorChangeUseCase,
) : AndroidViewModel(application) {

    private val _filterFactor = MutableLiveData<String>()
    val readAllEvent: LiveData<List<EventTypes>> = daoDataUseCase.getAll()

    init {
        _filterFactor.value = "title"
    }

    fun setSearchFactor(factor: String) {
        _filterFactor.postValue(factor)
    }

    fun deleteEvent(eventTypes: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventTypes)
        }
    }

    fun filterEventsWithFactor(inputData: String): List<EventTypes> {
        return searchFactorChangeUseCase(inputData, _filterFactor.value ?: "null")
    }
}
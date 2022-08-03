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

    private val _filterFactor = MutableLiveData<String>("title")
    val filterFactor: LiveData<String> = _filterFactor
    private val _keyWord = MutableLiveData<String>("")
    val keyWord: LiveData<String> = _keyWord
    val readAllEvent: LiveData<List<Event>> = daoDataUseCase.getAll()

    fun setSearchFactor(factor: String) {
        _filterFactor.postValue(factor)
    }

    fun setKeyWord(keyWord: String) {
        _keyWord.postValue(keyWord)
    }

    fun deleteEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventTypes)
        }
    }

    fun addEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.insertEvent(eventTypes)
        }
    }

    fun getSearchEvent(keyWord: String): List<Event> {
        return if (keyWord.isEmpty())
            emptyList()
        else
            getEventByKeyWordUseCase(keyWord, _filterFactor.value ?: "null")
    }
}
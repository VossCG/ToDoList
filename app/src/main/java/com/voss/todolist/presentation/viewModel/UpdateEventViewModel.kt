package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.domain.GetDateIntegerUseCase
import com.voss.todolist.domain.GetFormatDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateEventViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val formatDateUseCase: GetFormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : AndroidViewModel(application) {

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> get() = _date

    private val _type = MutableLiveData<String>("")
    val type: LiveData<String> get() = _type

    private val _title = MutableLiveData<String>("")
    val title: LiveData<String> get() = _title

    private val _content = MutableLiveData<String>("")
    val content: LiveData<String> get() = _content

    private val _dateInteger = MutableLiveData<Int>()
    val dateInteger: LiveData<Int> get() = _dateInteger

    fun setDate(date: String) {
        _date.postValue(date)
    }

    fun setType(type: String) {
        _type.postValue(type)
    }

    fun setTitle(title: String) {
        _title.postValue(title)
    }

    fun setContent(content: String) {
        _content.postValue(content)
    }

    fun setDateInteger(dateInteger: Int) {
        _dateInteger.postValue(dateInteger)
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return formatDateUseCase.invoke(year, month, day)
    }
    fun updateEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(eventTypes)
        }
    }
    fun getDateInteger(year: Int, month: Int, day: Int): Int {
        return getDateIntegerUseCase(year, month, day)
    }
}
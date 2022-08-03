package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.Event
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.GetFormatDateUseCase
import com.voss.todolist.domain.GetDateIntegerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val getFormatDateUseCase: GetFormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : AndroidViewModel(application) {

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> get() = _year

    private val _month = MutableLiveData<Int>()
    val month: LiveData<Int> get() = _month

    private val _day = MutableLiveData<Int>()
    val day: LiveData<Int> get() = _day

    private val _type = MutableLiveData<String>("工作")
    val type: LiveData<String> get() = _type

    private val _title = MutableLiveData<String>("")
    val title: LiveData<String> get() = _title

    private val _content = MutableLiveData<String>("")
    val content: LiveData<String> get() = _content

    fun setYear(year: Int) {
        _year.postValue(year)
    }

    fun setMonth(month: Int) {
        _month.postValue(month)
    }

    fun setDay(day: Int) {
        _day.postValue(day)
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

    fun insertEvent(eventTypes: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.insertEvent(eventTypes)
        }
    }

    fun getDateInteger(): Int {
        return getDateIntegerUseCase.invoke(
            _year.value!!,
            _month.value!!,
            _day.value!!
        )
    }

    fun getCurrentDate(): String {
        return getFormatDateUseCase.invoke(
            _year.value!!,
            _month.value!! - 1,
            _day.value!!
        )
    }

    fun getFormatData(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month, day)
    }
}
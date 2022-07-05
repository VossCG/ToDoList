package com.voss.todolist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.voss.todolist.data.EventTypes
import com.voss.todolist.domain.DaoDataUseCase
import com.voss.todolist.domain.FormatDateUseCase
import com.voss.todolist.domain.GetDateIntegerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val getDateIntegerUseCase: GetDateIntegerUseCase
) : AndroidViewModel(application) {
    private val calendar = Calendar.getInstance(Locale.TAIWAN)

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> get() = _year

    private val _month = MutableLiveData<Int>()
    val month: LiveData<Int> get() = _month

    private val _day = MutableLiveData<Int>()
    val day: LiveData<Int> get() = _day

    init {
        _year.value = calendar.get(Calendar.YEAR)
        _month.value = calendar.get(Calendar.MONTH)
        _day.value = calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun setYear(year: Int) {
        _year.postValue(year)
    }

    fun setMonth(month: Int) {
        _month.postValue(month)
    }

    fun setDay(day: Int) {
        _day.postValue(day)
    }

    fun addEvent(eventTypes: EventTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.addEvent(eventTypes)
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
        return formatDateUseCase.invoke(
            _year.value!!,
            _month.value!!,
            _day.value!!
        )
    }
}
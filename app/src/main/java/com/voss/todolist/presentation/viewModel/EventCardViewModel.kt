package com.voss.todolist.presentation.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.voss.todolist.data.Event
import com.voss.todolist.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventCardViewModel @Inject constructor(
    application: Application,
    private val daoDataUseCase: DaoDataUseCase,
    private val getSingleDayEventUseCase: GetSingleDayEventUseCase,
    private val getFormatDateUseCase: GetFormatDateUseCase
) : AndroidViewModel(application) {

    val readAllEvent: LiveData<List<Event>> = daoDataUseCase.getAll()

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    fun setDate(date: String) {
        _date.postValue(date)
    }

    fun deleteEvent(eventType: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDataUseCase.deleteEvent(eventType)
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "刪除完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getSingleDayEvent(date: String): List<Event> {
        return getSingleDayEventUseCase.invoke(date = date)
    }

    fun getDateFormat(year: Int, month: Int, day: Int): String {
        return getFormatDateUseCase.invoke(year, month - 1, day)
    }

}

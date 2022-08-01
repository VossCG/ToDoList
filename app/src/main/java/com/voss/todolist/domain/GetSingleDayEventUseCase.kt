package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import javax.inject.Inject

class GetSingleDayEventUseCase @Inject constructor(private val repository: EventRepository) {

    operator fun invoke(year: Int, month: Int, day: Int): List<Event> {
        return repository.eventDataList.value?.filter {
            it.getYear() == year && it.getMonth() == month && it.getDay() == day
        } ?: emptyList()
    }

    operator fun invoke(date: String): List<Event> {
        // use subString to get  year,month,day
        val year = date.subSequence(0..3).toString().toInt()
        val month = date.subSequence(5..6).toString().toInt()
        val day = date.subSequence(8..9).toString().toInt()
        return repository.eventDataList.value?.filter {
            it.getYear() == year && it.getMonth() == month && it.getDay() == day
        } ?: emptyList()
    }
}
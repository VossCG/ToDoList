package com.voss.todolist.domain

import com.voss.todolist.data.Event
import com.voss.todolist.data.EventRepository
import com.voss.todolist.util.getDay
import com.voss.todolist.util.getMonth
import com.voss.todolist.util.getYear
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSingleDayEventUseCase {

    operator fun invoke(date: String, repository: EventRepository): Flow<List<Event>> {
        return repository.eventFlow.map { events -> events.filter { it.date == date } }
    }

    operator fun invoke(date: String, events: List<Event>): List<Event> {
        // use subString to get  year,month,day
        val year = date.subSequence(0..3).toString().toInt()
        val month = date.subSequence(5..6).toString().toInt()
        val day = date.subSequence(8..9).toString().toInt()
        return events.filter {
            it.getYear() == year && it.getMonth() == month && it.getDay() == day
        }
    }
}
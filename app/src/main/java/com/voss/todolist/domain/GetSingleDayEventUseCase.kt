package com.voss.todolist.domain

import com.voss.todolist.data.Event

class GetSingleDayEventUseCase {

    operator fun invoke(year: Int, month: Int, day: Int, events: List<Event>): List<Event> {
        return events.filter {
            it.getYear() == year && it.getMonth() == month && it.getDay() == day
        }
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
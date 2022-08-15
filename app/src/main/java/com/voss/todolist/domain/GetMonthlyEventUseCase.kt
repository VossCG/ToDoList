package com.voss.todolist.domain

import com.voss.todolist.data.Event

class GetMonthlyEventUseCase  {

    operator fun invoke (year: Int, month: Int,events:List<Event>): List<Event> {
        return events.filter {
            it.getYear() == year && it.getMonth() == month
        }
    }
}
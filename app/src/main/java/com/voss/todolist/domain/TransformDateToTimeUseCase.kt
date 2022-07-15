package com.voss.todolist.domain

import com.voss.todolist.data.Event

class TransformDateToTimeUseCase {
    operator fun invoke(date: String): Triple<Int,Int,Int> {
        // use subString to get  year,month,day
        val year = date.subSequence(0..3).toString().toInt()
        val month = date.subSequence(5..6).toString().toInt()
        val day = date.subSequence(8..9).toString().toInt()
        return Triple(year,month,day)
    }
}
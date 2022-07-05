package com.voss.todolist.domain

import android.icu.text.SimpleDateFormat
import java.util.*

class FormatDateUseCase {
    private val formatDate = SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN)
    private val calendar =  Calendar.getInstance(Locale.TAIWAN)

    operator fun invoke(year: Int, month: Int, day: Int): String {
        calendar.set(year, month, day)
        val date = calendar.time
        return formatDate.format(date)
    }
    // get current Date without any attribute
    operator fun invoke(calendar: Calendar):String{
        return formatDate.format(calendar)
    }
}
package com.voss.todolist.domain

import android.icu.text.SimpleDateFormat
import com.voss.todolist.data.EventRepositoryImp
import java.util.*
import javax.inject.Inject

class FormatDateUseCase @Inject constructor(repository: EventRepositoryImp) {
    private val formatDate = SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN)

    operator fun invoke(year: Int, month: Int, day: Int, calendar: Calendar): String {
        calendar.set(year, month, day)
        val date = calendar.time
        return formatDate.format(date)
    }
}
package com.voss.todolist.domain

class GetDateIntegerUseCase {

    operator fun invoke(year: Int, month: Int, day: Int): Int {
        return year * 10000 + month * 100 + day
    }
}
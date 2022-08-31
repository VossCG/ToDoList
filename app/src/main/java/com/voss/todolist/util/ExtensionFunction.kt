package com.voss.todolist.util

import com.voss.todolist.data.Event


fun Event.getDay(): Int {
    return date.substring(8).toInt()
}
fun Event.getMonth(): Int {
    return date.substring(5, 7).toInt()
}
fun Event.getYear(): Int {
    return date.substring(0, 4).toInt()
}
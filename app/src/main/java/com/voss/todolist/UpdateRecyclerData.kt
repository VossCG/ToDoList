package com.voss.todolist

import com.voss.todolist.Data.EventTypes

interface UpdateRecyclerData {

    fun updateContentItem(data:EventTypes)

    fun deleteContentItem(data:EventTypes)
}
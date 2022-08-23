package com.voss.todolist.data.room

import com.voss.todolist.data.Event
import kotlinx.coroutines.flow.Flow

interface DateSource {

    suspend fun insert(event:Event)

    suspend fun delete(event: Event)

    suspend fun update(event: Event)

    fun getAll():Flow<List<Event>>

}
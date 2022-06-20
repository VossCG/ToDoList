package com.voss.todolist.Util

import androidx.recyclerview.widget.DiffUtil
import com.voss.todolist.Data.EventTypes

class EventTypeDiffUtil : DiffUtil.ItemCallback<EventTypes>() {
    override fun areItemsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem == newItem
    }
}
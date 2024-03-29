package com.voss.todolist.util

import androidx.recyclerview.widget.DiffUtil
import com.voss.todolist.data.Event

class EventTypeDiffUtil : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}
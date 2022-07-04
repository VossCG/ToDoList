package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.EventTypes
import com.voss.todolist.util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewDateEventTitleBinding

class HomeEventAdapter() : ListAdapter<EventTypes, HomeEventAdapter.EventTodayViewHolder>(EventTypeDiffUtil()) {

    var itemOnClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventTodayViewHolder {
        return EventTodayViewHolder(
            ItemviewDateEventTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventTodayViewHolder, position: Int) {
        holder.date.text = getItem(position).date.subSequence(5..9)
        holder.title.text = getItem(position).title
    }

    inner class EventTodayViewHolder(binding: ItemviewDateEventTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowTitleTextView
        val date: TextView = binding.rowDateTextView

        init {
            title.setOnClickListener {
                itemOnClick.invoke(adapterPosition)
            }
        }
    }
}



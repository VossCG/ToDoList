package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewEventListBinding

class CalendarEventListAdapter :
    ListAdapter<EventTypes, CalendarEventListAdapter.CalendarEventViewHolder>(EventTypeDiffUtil()) {

    var finishCallback: (data:EventTypes) -> Unit = {}
    var editCallback: (data:EventTypes) -> Unit = {}

    inner class CalendarEventViewHolder(val binding: ItemviewEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.itemEventTitle

        init {
            binding.itemEventTitle.setOnClickListener { editCallback.invoke(getItem(absoluteAdapterPosition)) }
            binding.itemEventFinishBut.setOnClickListener { finishCallback.invoke(getItem(absoluteAdapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarEventViewHolder {
        return CalendarEventViewHolder(
            ItemviewEventListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarEventViewHolder, position: Int) {
        holder.title.text = getItem(position).title
    }
}
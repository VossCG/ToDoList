package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.Util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewEventListBinding

class CalendarEventListAdapter :
    ListAdapter<EventTypes, CalendarEventListAdapter.CalendarEventViewHolder>(EventTypeDiffUtil()) {

    var getClickPosition: (position: Int) -> Unit = {}
    var itemDelete: (EventTypes) -> Unit = {}

    inner class CalendarEventViewHolder(val binding: ItemviewEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false
        val title = binding.itemEventTitle
        val content = binding.eventListItemContentTv

        init {
            binding.eventListItemDeleteBut.setOnClickListener {
                itemDelete.invoke(getItem(absoluteAdapterPosition))
            }

            binding.eventListItemExpandArrowBut.setOnClickListener {
                getClickPosition.invoke(absoluteAdapterPosition)
                if (isExpanded) {
                    binding.eventListItemExpandArrowBut.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.eventListItemExpandLayout.visibility = View.GONE
                    isExpanded = !isExpanded
                } else {
                    binding.eventListItemExpandArrowBut.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    binding.eventListItemExpandLayout.visibility = View.VISIBLE
                    isExpanded = !isExpanded
                }
            }
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
        holder.content.text = getItem(position).content

    }
}
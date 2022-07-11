package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewEventListBinding

class CalendarDayEventListAdapter : ListAdapter<EventTypes, CalendarDayEventListAdapter.CalendarEventViewHolder>(EventTypeDiffUtil()) {

    var clickItemDelete: (EventTypes) -> Unit = {}
    var clickITemUpdate: (EventTypes) -> Unit = {}
    var getExpandPosition: (position: Int) -> Unit = {}

    inner class CalendarEventViewHolder(val binding: ItemviewEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false
        val title = binding.itemEventTitle
        val content = binding.eventListItemContentTv
        val icon = binding.itemEventIcon

        init {
            binding.eventListItemDeleteBut.setOnClickListener {
                clickItemDelete.invoke(getItem(adapterPosition))
            }
            binding.eventListItemExpandArrowBut.setOnClickListener {
                getExpandPosition.invoke(adapterPosition)
                expandContent()
            }
            title.setOnClickListener {
                clickITemUpdate.invoke(getItem(adapterPosition))
            }
        }

        private fun expandContent() {
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
        when(getItem(position).type){
            "工作" -> holder.icon.setImageResource(R.drawable.ic_baseline_work_24)
            "出遊" -> holder.icon.setImageResource(R.drawable.ic_baseline_directions_car_24)
            "運動" -> holder.icon.setImageResource(R.drawable.ic_baseline_sports_tennis_24)
            "休閒" -> holder.icon.setImageResource(R.drawable.ic_baseline_weekend_24)
            "聚餐" -> holder.icon.setImageResource(R.drawable.ic_baseline_dining_24)
        }
    }
}
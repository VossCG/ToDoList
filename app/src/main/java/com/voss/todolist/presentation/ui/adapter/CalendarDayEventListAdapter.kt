package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.R
import com.voss.todolist.util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewEventListBinding

class CalendarDayEventListAdapter : ListAdapter<Event, CalendarDayEventListAdapter.CalendarEventViewHolder>(EventTypeDiffUtil()) {

    var clickITemUpdate: (Event) -> Unit = {}
    var navigateToContentCard : (Int)-> Unit = {}

    inner class CalendarEventViewHolder(val binding: ItemviewEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.itemEventTitle
        val icon = binding.itemEventIcon

        init {
            binding.eventListItemExpandArrowBut.setOnClickListener {
                navigateToContentCard.invoke(adapterPosition)
            }
            title.setOnClickListener {
                clickITemUpdate.invoke(getItem(adapterPosition))
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

        when(getItem(position).type){
            "工作" -> holder.icon.setImageResource(R.drawable.ic_baseline_work_24)
            "出遊" -> holder.icon.setImageResource(R.drawable.ic_baseline_directions_car_24)
            "運動" -> holder.icon.setImageResource(R.drawable.ic_baseline_sports_tennis_24)
            "休閒" -> holder.icon.setImageResource(R.drawable.ic_baseline_weekend_24)
            "學習" -> holder.icon.setImageResource(R.drawable.ic_baseline_menu_book_24)
            "聚餐" -> holder.icon.setImageResource(R.drawable.ic_baseline_dining_24)
        }
    }
}
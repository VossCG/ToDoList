package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.R
import com.voss.todolist.databinding.ItemviewDayEventCardBinding
import com.voss.todolist.util.EventTypeDiffUtil

class CalendarDayEventListAdapter :
    ListAdapter<Event, CalendarDayEventListAdapter.CalendarEventViewHolder>(EventTypeDiffUtil()) {

    var clickITemUpdate: (Event) -> Unit = {}
    var navigateToContentCard: (Int) -> Unit = {}

    inner class CalendarEventViewHolder(val binding: ItemviewDayEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.dayEventItemTitleTv
        val icon = binding.dayEventItemIconIv

        init {
            binding.dayEventItemExpandBtn.setOnClickListener {
                navigateToContentCard.invoke(adapterPosition)
            }
            title.setOnClickListener {
                clickITemUpdate.invoke(getItem(adapterPosition))
            }
        }

        fun bind(event: Event) {
            title.text = event.title

            when (event.type) {
                "工作" -> icon.setImageResource(R.drawable.ic_baseline_work_24)
                "出遊" -> icon.setImageResource(R.drawable.ic_baseline_directions_car_24)
                "運動" -> icon.setImageResource(R.drawable.ic_baseline_sports_tennis_24)
                "休閒" -> icon.setImageResource(R.drawable.ic_baseline_weekend_24)
                "學習" -> icon.setImageResource(R.drawable.ic_baseline_menu_book_24)
                "聚餐" -> icon.setImageResource(R.drawable.ic_baseline_dining_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarEventViewHolder {
        return CalendarEventViewHolder(
            ItemviewDayEventCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
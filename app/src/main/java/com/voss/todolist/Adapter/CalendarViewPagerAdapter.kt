package com.voss.todolist.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.R
import com.voss.todolist.databinding.ItemviewCalendarGridviewBinding


class CalendarViewPagerAdapter(private val context: Context) :
    RecyclerView.Adapter<CalendarViewPagerAdapter.CalendarViewHolder>() {
    private var daysOfMonth: List<List<Int>> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            ItemviewCalendarGridviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.gridView.adapter = CalendarGridViewAdapter(context, daysOfMonth[position], R.layout.itemview_calendar_gridview_icon)
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    fun submit(newList: List<List<Int>>) {
        daysOfMonth = newList
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(binding: ItemviewCalendarGridviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gridView = binding.calendarGridview

    }
}



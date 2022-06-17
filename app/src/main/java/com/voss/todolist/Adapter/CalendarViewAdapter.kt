package com.voss.todolist.Adapter

import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.databinding.ItemviewCalendarGridviewIconBinding

class CalendarViewAdapter : RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {
    private var days: List<Int> = emptyList()
    var getDrawableCallBack: ((String) -> Drawable)? = null
    private lateinit var oldSelectItemView: View
    private var isFirstSelected = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            ItemviewCalendarGridviewIconBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.dateTextView.text = days[position].toString()
        when (position) {
            5 -> holder.dateTextView.background = getDrawableCallBack?.invoke("single")
            15 -> holder.dateTextView.background = getDrawableCallBack?.invoke("some")
            25 -> holder.dateTextView.background = getDrawableCallBack?.invoke("multitude")
        }

    }

    override fun getItemCount(): Int {
        return days.size
    }

    fun setData(newList: List<Int>) {
        days = newList
    }

    inner class CalendarViewHolder(binding: ItemviewCalendarGridviewIconBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val dateTextView: TextView = binding.calendarGridviewItemTv
        val container: FrameLayout = binding.calendarGridviewItemRoot

        init {
            // calendarItem selected effect
            container.setOnClickListener {
                if (isFirstSelected) {
                    oldSelectItemView = it
                    it.background = getDrawableCallBack?.invoke("stroke")
                    isFirstSelected = false
                } else {
                    oldSelectItemView.background = getDrawableCallBack?.invoke("default")
                    it.background = getDrawableCallBack?.invoke("stroke")
                    oldSelectItemView = it
                }
            }
        }
    }
}

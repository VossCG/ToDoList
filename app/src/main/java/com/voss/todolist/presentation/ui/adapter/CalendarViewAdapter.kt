package com.voss.todolist.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.ItemviewCalendarIconBinding

class CalendarViewAdapter(private val dayOfMonth: Int, private val weekDayOffset: Int) :
    RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {
    private var event: List<Event> = emptyList()
    private var isFirstSelected = true
    private lateinit var oldSelectItemView: View

    var setDrawableCallBack : ((String) -> Drawable)? = null
    var getItemDayCallback : (Int) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            ItemviewCalendarIconBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        if (position >= weekDayOffset) {
            holder.bind(position)
            // if item in offset that it Gone , so can't selected
        } else holder.container.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return dayOfMonth + weekDayOffset
    }

    // set current event data
    fun setData(newList: List<Event>) {
        this.event = newList
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(binding: ItemviewCalendarIconBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val day: TextView = binding.calendarItemDayTv
        val container: FrameLayout = binding.calendarItemContainerFl

        init {
            // calendarItem selected effect
            container.setOnClickListener {
                showSelectedCursor(it)
            }
        }

        fun bind(position: Int) {
            val currentDay = position - weekDayOffset + 1
            day.text = currentDay.toString()
            // 當事件數量 到某一個數值時候，顯示不同的顏色
            showItemEventSizeDiff(currentDay)

        }
        private fun showItemEventSizeDiff(currentDay:Int){
            val currentDayEvent: List<Event> = event.filter {
                it.getDay() == currentDay
            }
            when (currentDayEvent.size) {
                0 -> day.background = setDrawableCallBack?.invoke("default")
                1 -> day.background = setDrawableCallBack?.invoke("single")
                2 -> day.background = setDrawableCallBack?.invoke("some")
                else -> day.background = setDrawableCallBack?.invoke("multitude")
            }
        }

        private fun showSelectedCursor(view: View) {
            if (adapterPosition >= weekDayOffset) {
                getItemDayCallback.invoke(adapterPosition + 1 - weekDayOffset)
                if (isFirstSelected) {
                    oldSelectItemView = view
                    view.background = setDrawableCallBack?.invoke("stroke")
                    isFirstSelected = false
                } else {
                    oldSelectItemView.background = setDrawableCallBack?.invoke("default")
                    view.background = setDrawableCallBack?.invoke("stroke")
                    oldSelectItemView = view
                }
            }
        }
    }
}

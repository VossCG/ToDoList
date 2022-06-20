package com.voss.todolist.Adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.databinding.ItemviewCalendarGridviewIconBinding

class CalendarViewAdapter(private val size: Int) :
    RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {
    private var event: List<EventTypes> = emptyList()

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

    // bind every date item and jude whether exist any event
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val currentDay = position+1
        holder.dateTextView.text = currentDay.toString()
        // get current event size
        val currentDayEvent: List<EventTypes> = event.filter {
            it.id == currentDay
        }
        // 當事件數量 到某一個數值時候，顯示不同的顏色
        when (currentDayEvent.size) {
            0 -> holder.dateTextView.background = getDrawableCallBack?.invoke("default")
            1 -> holder.dateTextView.background = getDrawableCallBack?.invoke("single")
            2 -> holder.dateTextView.background = getDrawableCallBack?.invoke("some")
            else -> holder.dateTextView.background = getDrawableCallBack?.invoke("multitude")
        }
    }


    override fun getItemCount(): Int {
        return size
    }

    // set current event data
    fun setData(newList: List<EventTypes>) {
        event = newList
        notifyDataSetChanged()
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

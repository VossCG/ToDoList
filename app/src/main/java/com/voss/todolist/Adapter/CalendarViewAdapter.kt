package com.voss.todolist.Adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.databinding.ItemviewCalendarGridviewIconBinding

class CalendarViewAdapter(private val size: Int, private val weekDayOffset: Int) :
    RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {
    private var event: List<EventTypes> = emptyList()
    private lateinit var oldSelectItemView: View
    private var isFirstSelected = true
    var getDrawableCallBack: ((String) -> Drawable)? = null
    var getItemDay: (Int) -> Unit = { }
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
        // because position start from 0
        Log.d("CalendarViewAdapter","offset:$weekDayOffset")
        if (position >= weekDayOffset) {
            Log.d("CalendarViewAdapter","onBind positions:$position")
            val currentDay = position - weekDayOffset + 1
            holder.dateTextView.text = currentDay.toString()
            val currentDayEvent: List<EventTypes> = event.filter {
                it.getDay() == currentDay
            }
            // 當事件數量 到某一個數值時候，顯示不同的顏色
            when (currentDayEvent.size) {
                0 -> holder.dateTextView.background = getDrawableCallBack?.invoke("default")
                1 -> holder.dateTextView.background = getDrawableCallBack?.invoke("single")
                2 -> holder.dateTextView.background = getDrawableCallBack?.invoke("some")
                else -> holder.dateTextView.background = getDrawableCallBack?.invoke("multitude")
            }
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
                // send back the item click position to notify what the day is
                if (absoluteAdapterPosition >= weekDayOffset){
                    getItemDay.invoke(absoluteAdapterPosition + 1 - weekDayOffset)
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
}

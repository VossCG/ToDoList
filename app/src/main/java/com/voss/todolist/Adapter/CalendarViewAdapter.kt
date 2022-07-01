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

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        if (position >= weekDayOffset) {
            holder.bind(position)
            // if item in offset that it Gone , so can't selected
        } else holder.container.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return size
    }

    // set current event data
    fun setData(newList: List<EventTypes>) {
        this.event = newList
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(binding: ItemviewCalendarGridviewIconBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val dateTextView: TextView = binding.calendarGridviewItemTv
        val container: FrameLayout = binding.calendarGridviewItemRoot

        init {
            // calendarItem selected effect
            container.setOnClickListener {
                setSelectedCursor(it)
            }
        }

        fun bind(position: Int){
            val currentDay = position - weekDayOffset + 1
            dateTextView.text = currentDay.toString()
            val currentDayEvent: List<EventTypes> = event.filter {
                it.getDay() == currentDay
            }
            // 當事件數量 到某一個數值時候，顯示不同的顏色
            when (currentDayEvent.size) {
                0 -> dateTextView.background = getDrawableCallBack?.invoke("default")
                1 -> dateTextView.background = getDrawableCallBack?.invoke("single")
                2 -> dateTextView.background = getDrawableCallBack?.invoke("some")
                else -> dateTextView.background = getDrawableCallBack?.invoke("multitude")
            }
        }

        private fun setSelectedCursor(view: View) {
            if (adapterPosition >= weekDayOffset) {
                getItemDay.invoke(adapterPosition + 1 - weekDayOffset)
                if (isFirstSelected) {
                    oldSelectItemView = view
                    view.background = getDrawableCallBack?.invoke("stroke")
                    isFirstSelected = false
                } else {
                    oldSelectItemView.background = getDrawableCallBack?.invoke("default")
                    view.background = getDrawableCallBack?.invoke("stroke")
                    oldSelectItemView = view
                }
            }
        }
    }
}

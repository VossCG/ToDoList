package com.voss.todolist.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import com.voss.todolist.databinding.ItemviewCalendarGridviewIconBinding

class CalendarGridViewAdapter(
    context: Context,
    private val days: List<Int>, private val res: Int
) : ArrayAdapter<CalendarGridViewAdapter.GridViewViewHolder>(context, res) {
    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private lateinit var binding:ItemviewCalendarGridviewIconBinding
    override fun getCount(): Int {
        return days.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: GridViewViewHolder
        if (convertView == null) {
            binding = ItemviewCalendarGridviewIconBinding.inflate(inflater,parent,false)
            convertView = binding.root
            holder = GridViewViewHolder()
            holder.dayTextView = binding.calendarGridviewItemTv
            holder.container = binding.calendarGridviewItemRoot
            convertView.tag = holder
        }else{
            holder = convertView.tag as GridViewViewHolder
        }
        holder.dayTextView!!.text = days[position].toString()
        return convertView
    }

    inner class GridViewViewHolder() {
        var dayTextView: TextView? = null
        var container: FrameLayout? = null
    }
}

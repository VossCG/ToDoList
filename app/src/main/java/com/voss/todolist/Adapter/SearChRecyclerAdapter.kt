package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.Util.ListAdapterDiffUtil
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.ItemviewSearchEventCardviewBinding

class SearChRecyclerAdapter() :
    ListAdapter<EventTypes, SearChRecyclerAdapter.SearChViewHolder>(ListAdapterDiffUtil()) {

    var itemClick : (Int)->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearChViewHolder {
        return SearChViewHolder(
            ItemviewSearchEventCardviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: SearChViewHolder, position: Int) {
        holder.title.text = getItem(position).title
        holder.month.text = (getItem(position).month + 1).toString() + "月"
        holder.day.text = getItem(position).day.toString()
        holder.content.text = getItem(position).content

    }

    inner class SearChViewHolder(binding: ItemviewSearchEventCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowSearchTitleTextView
        val month: TextView = binding.rowSearchMonthTextView
        val day: TextView = binding.rowSearchDayTextView
        val expand = binding.rowSearchExpandLayout
        val content = binding.rowSearchContentTextView
        val complete = binding.rowSearchContentCompletebut
        val arrow = binding.expandArrowImg
        val cardView = binding.rowSearchCardView

        init {

            cardView.setOnClickListener {
                if (expand.visibility == View.GONE) {
                    expand.visibility = View.VISIBLE
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                } else {
                    expand.visibility = View.GONE
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
                }
            }
            complete.setOnClickListener {
               itemClick.invoke(absoluteAdapterPosition)
            }

        }

    }
}
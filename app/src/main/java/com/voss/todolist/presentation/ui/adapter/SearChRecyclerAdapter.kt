package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.R
import com.voss.todolist.util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewSearchEventCardviewBinding

class SearChRecyclerAdapter() :
    ListAdapter<Event, SearChRecyclerAdapter.SearChViewHolder>(EventTypeDiffUtil()) {

    var itemClick: (Int) -> Unit = {}
    var itemDelete: (Event) -> Unit = {}
    var itemUpdate: (Event) -> Unit = {}

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
        holder.month.text = (getItem(position).getMonth() ).toString() + "月"
        holder.day.text = getItem(position).getDay().toString()
        holder.content.text = getItem(position).content

    }

    inner class SearChViewHolder(binding: ItemviewSearchEventCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowSearchTitleTextView
        val month: TextView = binding.rowSearchMonthTextView
        val day: TextView = binding.rowSearchDayTextView
        val content = binding.rowSearchContentTextView

        init {
            val expandListener = View.OnClickListener {
                expandEvent(binding)
            }

            binding.expandArrowBut.setOnClickListener(expandListener)
            binding.root.setOnClickListener(expandListener)

            binding.root.setOnLongClickListener {
                itemUpdate.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }

            binding.rowSearchContentCompletebut.setOnClickListener {
                itemDelete.invoke(getItem(adapterPosition))
            }
        }

        private fun expandEvent(binding: ItemviewSearchEventCardviewBinding) {
            if (binding.rowSearchExpandLayout.visibility == View.GONE) {
                binding.rowSearchExpandLayout.visibility = View.VISIBLE
                binding.expandArrowBut.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                itemClick.invoke(adapterPosition)
            } else {
                binding.rowSearchExpandLayout.visibility = View.GONE
                binding.expandArrowBut.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
            }
        }

    }
}